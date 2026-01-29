import java.util.Scanner;

public class UserInterface {
    private static Utility utility;

    public static void main(String[] args) {
        utility = new Utility();
        Scanner sc = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("\nEnter the Goods Transport details:");
            String input = sc.nextLine();
            
            GoodsTransport goodsTransport = utility.parseDetails(input);
            
            // Check if parsing was successful
            if (goodsTransport == null) {
                System.out.println("Invalid transport type provided");
                continue;
            }
            
            // Validate transport ID
            if (!utility.validateTransportId(goodsTransport.getTransportId())) {
                System.out.println("Transport id " + goodsTransport.getTransportId() + " is invalid");
                System.out.println("Please provide a valid record");
                continueLoop = false;
                break;
            }
            
            // Determine object type
            String objectType = utility.findObjectType(goodsTransport);
            
            // Calculate total charge based on object type
            double totalCharge = 0;
            if (objectType.equals("BrickTransport")) {
                BrickTransport brickTransport = (BrickTransport) goodsTransport;
                totalCharge = brickTransport.calculateTotalCharge();
                displayBrickTransportDetails(brickTransport, totalCharge);
            } else if (objectType.equals("TimberTransport")) {
                TimberTransport timberTransport = (TimberTransport) goodsTransport;
                totalCharge = timberTransport.calculateTotalCharge();
                displayTimberTransportDetails(timberTransport, totalCharge);
            } else {
                System.out.println("Invalid transport type");
            }
        }
        
        sc.close();
    }

    private static void displayBrickTransportDetails(BrickTransport brickTransport, double totalCharge) {
        System.out.println("\nTransporter id : " + brickTransport.getTransportId());
        System.out.println("Date of transport : " + brickTransport.getTranportDate());
        System.out.println("Rating of the transport : " + brickTransport.getTranportRating());
        System.out.println("Quantity of bricks : " + brickTransport.getBrickQuantity());
        System.out.println("Brick price : " + brickTransport.getBrickPrice());
        System.out.println("Vehicle for transport : " + brickTransport.vehicleSelection());
        System.out.println("Total charge : " + totalCharge);
    }

    private static void displayTimberTransportDetails(TimberTransport timberTransport, double totalCharge) {
        System.out.println("\nTransporter id : " + timberTransport.getTransportId());
        System.out.println("Date of transport : " + timberTransport.getTranportDate());
        System.out.println("Rating of the transport : " + timberTransport.getTranportRating());
        System.out.println("Timber length : " + timberTransport.getTimberLength());
        System.out.println("Timber radius : " + timberTransport.getTimberRadius());
        System.out.println("Timber type : " + timberTransport.getTimberType());
        System.out.println("Timber price : " + timberTransport.getTimberPrice());
        System.out.println("Vehicle for transport : " + timberTransport.vehicleSelection());
        System.out.println("Total charge : " + totalCharge);
    }

}
