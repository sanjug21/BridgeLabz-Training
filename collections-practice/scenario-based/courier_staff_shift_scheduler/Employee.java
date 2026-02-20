class Employee {
    String employeeId;
    String name;
    String phone;

    public Employee(String employeeId, String name, String phone) {
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return employeeId + " | " + name + " | " + phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return employeeId.equals(employee.employeeId);
    }

    @Override
    public int hashCode() {
        return employeeId.hashCode();
    }
}
