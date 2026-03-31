package library_management_system;
public class Staff extends Member {
    public Staff(String name, String memberId) {
        super(name, memberId);
    }

    @Override
    public double calculateFine(int daysLate) {
        // Staff fine: Rs 5 per day (Staff gets a discount)
        return daysLate > 0 ? daysLate * 5.0 : 0.0;
    }
}