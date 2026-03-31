public class LeaveRequest {
    String employeeId;
    int days;
    String status;

    public LeaveRequest(String employeeId, int days) {
        this.employeeId = employeeId;
        this.days = days;
        this.status = "PENDING";
    }

    @Override
    public String toString() {
        return employeeId + " | Days: " + days + " | Status: " + status;
    }
}
