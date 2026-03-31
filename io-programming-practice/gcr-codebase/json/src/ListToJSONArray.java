import org.json.JSONObject;
import org.json.JSONArray;

public class ListToJSONArray {
    static class Employee {
        int id;
        String name;
        String designation;
        double salary;

        Employee(int id, String name, String designation, double salary) {
            this.id = id;
            this.name = name;
            this.designation = designation;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(1, "Rajesh Kumar", "Senior Developer", 75000),
                new Employee(2, "Priya Singh", "Project Manager", 65000),
                new Employee(3, "Amit Patel", "QA Engineer", 55000)
        };

        JSONArray employeeArray = new JSONArray();
        for (Employee emp : employees) {
            JSONObject empJSON = new JSONObject();
            empJSON.put("id", emp.id);
            empJSON.put("name", emp.name);
            empJSON.put("designation", emp.designation);
            empJSON.put("salary", emp.salary);
            employeeArray.put(empJSON);
        }

        System.out.println(employeeArray.toString(4));
    }
}