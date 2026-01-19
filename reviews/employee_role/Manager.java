package employee_role;

public class Manager extends Employee {
    private  double bonus;
    private final double BONUS_RATE=10;
    Manager(String name,double salary){
        super(name, salary);
        this.bonus=0;
    }
    @Override
    public double getBonus(){
        bonus=getSalary()*BONUS_RATE/100;
        return bonus;
    }
}
