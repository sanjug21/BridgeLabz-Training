
public class AuctionBiddingApp {

    public static void main(String[] args) {
        AuctionSystem auction = new AuctionSystem();

        User user1 = new User("U001", "Alice", 5000);
        User user2 = new User("U002", "Bob", 8000);
        User user3 = new User("U003", "Charlie", 3000);

        auction.registerUser(user1);
        auction.registerUser(user2);
        auction.registerUser(user3);

        AuctionItem item1 = new AuctionItem("A001", "Vintage Laptop", 1000);
        AuctionItem item2 = new AuctionItem("A002", "Gold Watch", 500);

        auction.addAuctionItem(item1);
        auction.addAuctionItem(item2);

        auction.displayAllAuctions();

        System.out.println("\n=== Placing Bids ===");

        try {
            auction.placeBid("U001", "A001", 1500);
            auction.placeBid("U002", "A001", 2000);
            auction.placeBid("U001", "A001", 2500);
            auction.placeBid("U003", "A001", 3000);

            System.out.println("\nTrying to place lower bid...");
            auction.placeBid("U002", "A001", 2000);

        } catch (InvalidBidException e) {
            System.out.println("Bid Error: " + e.getMessage());
        }

        try {
            auction.placeBid("U002", "A002", 600);
            auction.placeBid("U001", "A002", 700);

            System.out.println("\nTrying to exceed wallet balance...");
            auction.placeBid("U003", "A002", 5000);

        } catch (InvalidBidException e) {
            System.out.println("Bid Error: " + e.getMessage());
        }

        auction.displayAuctionDetails("A001");
        auction.displayAuctionDetails("A002");

        System.out.println("\n=== Closing Auctions ===");
        item1.closeAuction();
        item2.closeAuction();

        try {
            System.out.println("\nTrying to place bid on closed auction...");
            auction.placeBid("U001", "A001", 5000);
        } catch (InvalidBidException e) {
            System.out.println("Bid Error: " + e.getMessage());
        }
    }
}
