package employee_role;

import java.util.Scanner;

public class EmployeeSalary {
    public static void main(String[] args) {
        Employee e1=new Manager("Sanju", 50000);
        Employee e2=new Developer("Shubham", 40000);
        Employee e3=new Developer("Yash", 60000);
        Employee e4=new Manager("Ritu", 90000);

        // System.out.println(e1.getName()+" bonus: "+e1.getBonus());
        // System.out.println(e2.getName()+" bonus: "+e2.getBonus());
        // System.out.println(e3.getName()+" bonus: "+e3.getBonus());

        Employee[] employees={e1,e2,e3,e4};

        // manually get bonus
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter name to get bonus:");
        String name=sc.next();
        boolean empFound=false;
        for(Employee e:employees){
            if(e.getName().equalsIgnoreCase(name)){
                System.out.println(e.getBonus());
                empFound=true;
                break;
            }
        }
        if(!empFound){
            System.out.println("No employee found with the name: "+name);
        }
        sc.close();
    }
}
