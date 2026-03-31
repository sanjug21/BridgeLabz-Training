
public class HotelBooking {
    String guestName;
    String roomType;
    int nights;
    public HotelBooking() {
    }
    public HotelBooking(String guestName,String roomType,int nights) {
        this.guestName=guestName;
        this.roomType=roomType;
        this.nights=nights;
    }
    public HotelBooking(HotelBooking other) {
        this.guestName=other.guestName;
        this.roomType=other.roomType;
        this.nights=other.nights;
    }
    public void display() {
        System.out.println("Guest Name: "+guestName+", Room Type: "+roomType+", Nights: "+nights);
    }
    public static void main(String[] args) {
        // Creating instances of HotelBooking using different constructors

        // Using default constructor
        HotelBooking booking1=new HotelBooking();
        // Using parameterized constructor
        HotelBooking booking2=new HotelBooking("Sanju","Deluxe",3);
        // Creating a copy of booking2 using the copy constructor
        HotelBooking booking3=new HotelBooking(booking2);

        // Displaying the details of each booking
        booking1.display();
        booking2.display();
        booking3.display();
    }
}