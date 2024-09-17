package org.example;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SecretSantaAssignerTest {

    Employee employee1 = new Employee("Employee 1", "employee1@email.com");
    Employee employee2 = new Employee("Employee 2", "employee2@email.com");
    Employee employee3 = new Employee("Employee 3", "employee3@email.com");


    @Test
    public void testAssignSecretSantas_EmptyEmployeeList_ThrowsException() {
        List<Employee> emptyList = Collections.emptyList();
        Map<Employee, Employee> lastYearAssignments = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> new SecretSantaAssigner(emptyList, lastYearAssignments));
    }

    @Test
    public void testAssignSecretSantas_NullEmployeeList_ThrowsException() {
        Map<Employee, Employee> lastYearAssignments = new HashMap<>();
        assertThrows(IllegalArgumentException.class, () -> new SecretSantaAssigner(null, lastYearAssignments));
    }

    @Test
    public void testAssignSecretSantas_NullLastYearAssignments_ThrowsException() {
        List<Employee> employees = Arrays.asList(employee1, employee2, employee3);
        assertThrows(IllegalArgumentException.class, () -> new SecretSantaAssigner(employees, null));
    }

    @Test
    public void testFindReceiver_NoValidReceivers_ReturnsNull() {
        List<Employee> availableEmployees = new ArrayList<>();
        availableEmployees.add(employee1);

        Set<Employee> assigned = new HashSet<>();
        assigned.add(employee1);

        SecretSantaAssigner assigner = Mockito.mock(SecretSantaAssigner.class, Mockito.CALLS_REAL_METHODS);
        Mockito.when(assigner.getEmployees()).thenReturn(Collections.singletonList(employee2));

        Employee receiver = assigner.findReceiver(availableEmployees, employee1, assigned);

        assertNull(receiver);
    }


}

