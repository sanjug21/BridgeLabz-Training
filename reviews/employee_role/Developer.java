package employee_role;

public class Developer extends Employee {
    private double bonus;
    private final double BONUS_RATE = 5;
    Developer(String name, double salary) {
        super(name, salary);
        this.bonus = 0;
    }
    @Override
    public double getBonus() {
        double salary=getSalary();
        if(salary<=50000){
            return bonus;
        }
        bonus=salary*BONUS_RATE/100;
        return bonus;
    }
}
