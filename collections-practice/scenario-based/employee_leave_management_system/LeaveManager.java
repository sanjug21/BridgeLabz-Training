import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveManager {
    private Map<String, Employee> employees = new HashMap<>();
    private List<LeaveRequest> requests = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.put(employee.id, employee);
    }

    public void requestLeave(String employeeId, int days) throws InsufficientLeaveBalanceException {
        Employee employee = employees.get(employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + employeeId);
        }
        if (employee.leaveBalance < days) {
            throw new InsufficientLeaveBalanceException("Insufficient balance for " + employee.name);
        }
        requests.add(new LeaveRequest(employeeId, days));
    }

    public void approveRequest(int index) {
        if (index < 0 || index >= requests.size()) {
            throw new IllegalArgumentException("Invalid request index");
        }
        LeaveRequest request = requests.get(index);
        if (!"PENDING".equals(request.status)) {
            return;
        }
        Employee employee = employees.get(request.employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + request.employeeId);
        }
        employee.leaveBalance -= request.days;
        request.status = "APPROVED";
    }

    public void rejectRequest(int index) {
        if (index < 0 || index >= requests.size()) {
            throw new IllegalArgumentException("Invalid request index");
        }
        LeaveRequest request = requests.get(index);
        if (!"PENDING".equals(request.status)) {
            return;
        }
        request.status = "REJECTED";
    }

    public void printEmployees() {
        for (Employee employee : employees.values()) {
            System.out.println(employee);
        }
    }

    public void printRequests() {
        for (int i = 0; i < requests.size(); i++) {
            System.out.println("#" + i + " " + requests.get(i));
        }
    }
}
