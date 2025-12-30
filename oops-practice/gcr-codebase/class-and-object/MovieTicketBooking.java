public class MovieTicketBooking {
    String movieName;
    String seatNumber;
    double price;
    boolean isBooked=false;

    public void bookTicket(String movieName, String seatNumber, double price){
        if(isBooked){
            System.out.println("House full!!! sorry..... Ticket already booked");
        }else{
            this.movieName=movieName;
            this.seatNumber=seatNumber;
            this.price=price;
            this.isBooked=true;
            System.out.println("Ticket booked for movie: "+movieName);
            System.out.println("Seat Number: "+seatNumber);
        }
    }

    public void display(){
        if(!isBooked){
            System.out.println("Ticket have not booked yet....");
        }else{
            System.out.println("Price: $"+price);
        }
    }

    public static void main(String[] args){
        MovieTicketBooking t=new MovieTicketBooking();
        t.display();
        t.bookTicket("Dragon", "A10", 120.0);
        t.bookTicket("Dragon", "A10", 120.0);
        t.bookTicket("Dragon", "A10", 120.0);
        t.display();
        
        System.out.println("\nTicket booked for movie: Dragon");
        System.out.println("Seat Number: A10");
        t.display();
    }
}