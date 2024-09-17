package org.example;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecretSantaAssigner {
    private final List<Employee> employees;
    private final Map<Employee, Employee> lastYearAssignments;
    private static final Logger LOGGER = Logger.getLogger(SecretSantaAssigner.class.getName());

    public List<Employee> getEmployees() {
        return employees;
    }

    public Map<Employee, Employee> getLastYearAssignments() {
        return lastYearAssignments;
    }

    public SecretSantaAssigner(List<Employee> employees, Map<Employee, Employee> lastYearAssignments) {
        if (employees == null || employees.isEmpty() || lastYearAssignments == null) {
            throw new IllegalArgumentException("Employees list and last year assignments map cannot be null.");
        }
        this.employees = employees;
        this.lastYearAssignments = lastYearAssignments;
    }

    public Map<Employee, Employee> assignSecretSantas() {
        List<Employee> available = new ArrayList<>(employees);
        Collections.shuffle(available);

        Map<Employee, Employee> assignments = new HashMap<>();
        Set<Employee> assigned = new HashSet<>();

        for (Employee giver : employees) {
            try {
                Employee receiver = findReceiver(available, giver, assigned);
                if (receiver == null) {
                    throw new IllegalStateException("No valid receiver found for " + giver);
                }
                assignments.put(giver, receiver);
                assigned.add(receiver);
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error assigning Secret Santa for " + giver.getName(), e);
                throw e; // rethrow the exception to signal failure
            }
        }

        return assignments;
    }

    public Employee findReceiver(List<Employee> available, Employee giver, Set<Employee> assigned) {
        List<Employee> possibleReceivers = new ArrayList<>();
        for (Employee receiver : available) {
            if (!receiver.equals(giver) &&
                    !receiver.equals(lastYearAssignments.get(giver)) && // Avoid previous year assignment
                    !assigned.contains(receiver)) { // Ensure receiver is not already assigned
                possibleReceivers.add(receiver);
            }
        }

        if (possibleReceivers.isEmpty()) {
            LOGGER.warning("No valid receivers found for " + giver.getName());
            return null; // No valid receiver found
        }

        Collections.shuffle(possibleReceivers);
        return possibleReceivers.get(0);
    }
}
