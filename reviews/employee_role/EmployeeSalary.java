package employee_role;

public class EmployeeSalary {
    public static void main(String[] args) {
        Employee e1=new Manager("Sanju", 50000);
        Employee e2=new Developer("Shubham", 40000);
        Employee e3=new Developer("Yash", 60000);

        System.out.println(e1.getName()+" bonus: "+e1.getBonus());
        System.out.println(e2.getName()+" bonus: "+e2.getBonus());
        System.out.println(e3.getName()+" bonus: "+e3.getBonus());
    }
}
