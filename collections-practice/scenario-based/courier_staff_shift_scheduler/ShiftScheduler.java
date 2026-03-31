import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftScheduler {
    private List<Employee> employees;
    private Map<ShiftTime, List<Employee>> shiftAssignments;

    public ShiftScheduler() {
        this.employees = new ArrayList<>();
        this.shiftAssignments = new HashMap<>();
    }

    public void addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Employee already exists: " + employee.employeeId);
            return;
        }
        employees.add(employee);
        System.out.println("Employee added: " + employee.name);
    }

    public void createShift(ShiftTime shift) {
        if (shiftAssignments.containsKey(shift)) {
            System.out.println("Shift already exists: " + shift.shiftName);
            return;
        }
        shiftAssignments.put(shift, new ArrayList<>());
        System.out.println("Shift created: " + shift);
    }

    public void assignShift(String employeeId, ShiftTime shift) throws ShiftAlreadyAssignedException {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found: " + employeeId);
            return;
        }

        if (!shiftAssignments.containsKey(shift)) {
            System.out.println("Shift not found: " + shift.shiftName);
            return;
        }

        if (isEmployeeAssignedToShift(employee, shift)) {
            throw new ShiftAlreadyAssignedException(
                "Employee " + employee.name + " is already assigned to " + shift.shiftName
            );
        }

        shiftAssignments.get(shift).add(employee);
        System.out.println("Assigned " + employee.name + " to " + shift.shiftName);
    }

    private boolean isEmployeeAssignedToShift(Employee employee, ShiftTime shift) {
        List<Employee> assignedEmployees = shiftAssignments.get(shift);
        return assignedEmployees.contains(employee);
    }

    private Employee findEmployeeById(String employeeId) {
        for (Employee emp : employees) {
            if (emp.employeeId.equals(employeeId)) {
                return emp;
            }
        }
        return null;
    }

    public void removeShiftAssignment(String employeeId, ShiftTime shift) {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found: " + employeeId);
            return;
        }

        if (!shiftAssignments.containsKey(shift)) {
            System.out.println("Shift not found: " + shift.shiftName);
            return;
        }

        List<Employee> assignedEmployees = shiftAssignments.get(shift);
        if (assignedEmployees.remove(employee)) {
            System.out.println("Removed " + employee.name + " from " + shift.shiftName);
        } else {
            System.out.println(employee.name + " is not assigned to " + shift.shiftName);
        }
    }

    public void displayAllEmployees() {
        System.out.println("\n--- All Employees ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    public void displayAllShifts() {
        System.out.println("\n--- All Shifts ---");
        if (shiftAssignments.isEmpty()) {
            System.out.println("No shifts created.");
            return;
        }
        for (ShiftTime shift : shiftAssignments.keySet()) {
            System.out.println(shift + " | Assigned: " + shiftAssignments.get(shift).size());
        }
    }

    public void displayShiftAssignments(ShiftTime shift) {
        if (!shiftAssignments.containsKey(shift)) {
            System.out.println("Shift not found: " + shift.shiftName);
            return;
        }

        List<Employee> assignedEmployees = shiftAssignments.get(shift);
        System.out.println("\n--- " + shift + " ---");
        System.out.println("Assigned Employees: " + assignedEmployees.size());

        if (assignedEmployees.isEmpty()) {
            System.out.println("No employees assigned.");
            return;
        }

        for (Employee emp : assignedEmployees) {
            System.out.println("  " + emp);
        }
    }

    public void displayAllShiftAssignments() {
        System.out.println("\n=======================================");
        System.out.println("      SHIFT ASSIGNMENT SCHEDULE");
        System.out.println("=======================================");

        if (shiftAssignments.isEmpty()) {
            System.out.println("No shifts created.");
            return;
        }

        for (Map.Entry<ShiftTime, List<Employee>> entry : shiftAssignments.entrySet()) {
            ShiftTime shift = entry.getKey();
            List<Employee> assignedEmployees = entry.getValue();

            System.out.println("\n" + shift);
            System.out.println("Employees: " + assignedEmployees.size());
            if (!assignedEmployees.isEmpty()) {
                for (Employee emp : assignedEmployees) {
                    System.out.println("  - " + emp.name + " (" + emp.employeeId + ")");
                }
            } else {
                System.out.println("  No employees assigned");
            }
        }
        System.out.println("\n=======================================");
    }

    public void displayStatistics() {
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Employees: " + employees.size());
        System.out.println("Total Shifts: " + shiftAssignments.size());

        int totalAssignments = 0;
        for (List<Employee> assignedEmployees : shiftAssignments.values()) {
            totalAssignments += assignedEmployees.size();
        }
        System.out.println("Total Assignments: " + totalAssignments);
    }

    public ShiftTime findShiftByName(String shiftName) {
        for (ShiftTime shift : shiftAssignments.keySet()) {
            if (shift.shiftName.equalsIgnoreCase(shiftName)) {
                return shift;
            }
        }
        return null;
    }
}
