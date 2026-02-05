import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }
}

public class EmployeeSalaryCategorization {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Riya", "IT", 72000));
        employees.add(new Employee("Vikram", "HR", 48000));
        employees.add(new Employee("Anita", "IT", 86000));
        employees.add(new Employee("Suresh", "Finance", 65000));
        employees.add(new Employee("Deepa", "HR", 52000));

        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("Average Salary By Department:");
        avgSalaryByDept.forEach((dept, avg) -> System.out.println(dept + " -> " + avg));
    }
}
