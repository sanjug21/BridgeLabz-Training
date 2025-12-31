
public class CarRental {
    String customerName;
    String carModel;
    int rentalDays;
    public CarRental(String customerName,String carModel,int rentalDays) {
        this.customerName=customerName;
        this.carModel=carModel;
        this.rentalDays=rentalDays;
    }
    public double calculateTotalCost(double dailyRate) {
        return rentalDays*dailyRate;
    }
    public static void main(String[] args) {
        // creating using parameterized constructor
        CarRental rental1=new CarRental("Sanju","Ferrari",5);
        // calculating total cost
        double totalCost=rental1.calculateTotalCost(50.0);
        // displaying details
        System.out.println("Customer Name: "+rental1.customerName+", Car Model: "+rental1.carModel+", Rental Days: "+rental1.rentalDays);
        System.out.println("Total Cost: "+totalCost);
    }
}