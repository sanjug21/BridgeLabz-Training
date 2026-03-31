public class Employee {
    String id;
    String name;
    int leaveBalance;

    public Employee(String id, String name, int leaveBalance) {
        this.id = id;
        this.name = name;
        this.leaveBalance = leaveBalance;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (Balance: " + leaveBalance + ")";
    }
}
