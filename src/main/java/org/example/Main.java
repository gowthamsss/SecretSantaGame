package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        try {
            String employeeFile = "src\\main\\java\\org\\example\\Employee-List.xlsx";
            String lastYearFile = "src\\main\\java\\org\\example\\Last-Year-Santa-List.xlsx";
            String outputFile = "src\\main\\java\\org\\example\\Secret-Santa-Game-Result.xlsx";

            // Parse input files
            List<Employee> employees = XLSXParser.parseEmployeeXLSX(employeeFile);
            Map<Employee, Employee> lastYearAssignments = XLSXParser.parseLastYearAssignments(lastYearFile);

            // Assign secret santas
            SecretSantaAssigner assigner = new SecretSantaAssigner(employees, lastYearAssignments);
            Map<Employee, Employee> newAssignments = assigner.assignSecretSantas();

            // Write new assignments to output file
            XLSXParser.writeResultsFile(outputFile, newAssignments, employees);

            System.out.println("Secret Santa assignments completed and saved to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("Error with Secret Santa assignment: " + e.getMessage());
        }
    }
}
