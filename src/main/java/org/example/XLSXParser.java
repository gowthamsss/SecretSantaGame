package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XLSXParser {
    public static List<Employee> parseEmployeeXLSX(String filePath) throws IOException {
        List<Employee> employees = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String name = row.getCell(0).getStringCellValue();
                String email = row.getCell(1).getStringCellValue();
                employees.add(new Employee(name, email));
            }
        }
        return employees;
    }

    public static Map<Employee, Employee> parseLastYearAssignments(String filePath) throws IOException {
        Map<Employee, Employee> assignments = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String giverName = row.getCell(0).getStringCellValue();
                String giverEmail = row.getCell(1).getStringCellValue();
                String receiverName = row.getCell(2).getStringCellValue();
                String receiverEmail = row.getCell(3).getStringCellValue();

                Employee giver = new Employee(giverName, giverEmail);
                Employee receiver = new Employee(receiverName, receiverEmail);

                assignments.put(giver, receiver);
            }
        }
        return assignments;
    }

    public static void writeResultsFile(String filePath, Map<Employee, Employee> assignments, List<Employee> employees) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Assignments");

            CellStyle yellowBackgroundStyle = workbook.createCellStyle();
            yellowBackgroundStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            yellowBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Create header row
            Row header = sheet.createRow(0);
            String[] headers = {"Employee_Name", "Employee_EmailID", "Secret_Child_Name", "Secret_Child_EmailID"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(yellowBackgroundStyle); // Apply the yellow background style
            }

            // Create data rows
            int rowNum = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(employee.getName());
                row.createCell(1).setCellValue(employee.getEmail());

                Employee secretChild = assignments.get(employee);
                if (secretChild != null) {
                    row.createCell(2).setCellValue(secretChild.getName());
                    row.createCell(3).setCellValue(secretChild.getEmail());
                } else {
                    row.createCell(2).setCellValue("N/A");
                    row.createCell(3).setCellValue("N/A");
                }
            }
            // To Auto size the column
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
                workbook.write(fos);
            }
        }
    }
}
