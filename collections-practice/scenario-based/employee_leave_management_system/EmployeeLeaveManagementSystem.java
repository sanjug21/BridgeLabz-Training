public class EmployeeLeaveManagementSystem {

    public static void main(String[] args) {
        LeaveManager manager = new LeaveManager();
        manager.addEmployee(new Employee("E101", "Asha", 12));
        manager.addEmployee(new Employee("E102", "Ravi", 6));
        manager.addEmployee(new Employee("E103", "Meera", 4));

        System.out.println("Employees:");
        manager.printEmployees();

        try {
            manager.requestLeave("E101", 5);
            manager.requestLeave("E102", 7);
            manager.requestLeave("E103", 2);
        } catch (InsufficientLeaveBalanceException ex) {
            System.out.println("Leave request failed: " + ex.getMessage());
        }

        System.out.println("\nLeave Requests:");
        manager.printRequests();

        manager.approveRequest(0);
        manager.rejectRequest(1);
        manager.approveRequest(2);

        System.out.println("\nRequests After Approval/Rejection:");
        manager.printRequests();

        System.out.println("\nEmployees After Processing:");
        manager.printEmployees();
    }
}
