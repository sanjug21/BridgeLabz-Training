import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Employee {
    String name;
    String department;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class EmployeeGrouper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Employees
        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        
        List<Employee> employees = new ArrayList<>();
        System.out.println("Enter Name and Department for each employee:");
        for(int i = 0; i < n; i++) {
            String name = sc.next();
            String dept = sc.next();
            employees.add(new Employee(name, dept));
        }

        // 2. Group by Department
        Map<String, List<Employee>> grouped = groupByDepartment(employees);
        System.out.println("Grouped Employees: " + grouped);

        sc.close();
    }

    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees) {
        Map<String, List<Employee>> map = new HashMap<>();
        for (Employee emp : employees) {
            map.putIfAbsent(emp.department, new ArrayList<>());
            map.get(emp.department).add(emp);
        }
        return map;
    }
}