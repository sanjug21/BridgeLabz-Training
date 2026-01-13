package library_management_system;

public class Student extends Member {
    public Student(String name, String memberId) {
        super(name, memberId);
    }

    @Override
    public double calculateFine(int daysLate) {
        // Student fine: Rs 10 per day
        return daysLate > 0 ? daysLate * 10.0 : 0.0;
    }
}