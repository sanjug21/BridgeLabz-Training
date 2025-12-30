public class EmployeeDetailsDisplay {
    
    String name;
    int id;
    double salary;

    public EmployeeDetailsDisplay(String n, int i, double s){
        this.name=n;
        this.id=i;
        this.salary=s;
    }

    public void display(){
        System.out.println("Employee Name: "+name);
        System.out.println("Employee id: "+id);
        System.out.println("Employee Salary: "+salary);
    }

    public static void main(String[] args){
        EmployeeDetailsDisplay emp=new EmployeeDetailsDisplay("Rohan", 1, 500000);
        emp.display();
    }
}