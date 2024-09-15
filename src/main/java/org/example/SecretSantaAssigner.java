package org.example;

import java.util.*;

public class SecretSantaAssigner {
    private final List<Employee> employees;
    private final Map<Employee, Employee> lastYearAssignments;

    public SecretSantaAssigner(List<Employee> employees, Map<Employee, Employee> lastYearAssignments) {
        this.employees = employees;
        this.lastYearAssignments = lastYearAssignments;
    }

    public Map<Employee, Employee> assignSecretSantas() {
        List<Employee> available = new ArrayList<>(employees);
        Collections.shuffle(available);

        Map<Employee, Employee> assignments = new HashMap<>();
        Set<Employee> assigned = new HashSet<>();

        for (Employee giver : employees) {
            Employee receiver = findReceiver(available, giver, assigned);
            if (receiver == null) {
                throw new IllegalStateException("No valid receiver found for " + giver);
            }
            assignments.put(giver, receiver);
            assigned.add(receiver);
        }

        return assignments;
    }

    private Employee findReceiver(List<Employee> available, Employee giver, Set<Employee> assigned) {
        List<Employee> possibleReceivers = new ArrayList<>();
        for (Employee receiver : available) {
            if (!receiver.equals(giver) &&
                    !receiver.equals(lastYearAssignments.get(giver)) && // Avoid previous year assignment
                    !assigned.contains(receiver)) { // Ensure receiver is not already assigned
                possibleReceivers.add(receiver);
            }
        }

        if (possibleReceivers.isEmpty()) {
            return null; // No valid receiver found
        }

        Collections.shuffle(possibleReceivers);
        return possibleReceivers.get(0);
    }
}
