import java.util.ArrayList;
import java.util.List;

class AuctionSystem {
    private List<AuctionItem> auctionItems;
    private List<User> users;

    public AuctionSystem() {
        auctionItems = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered: " + user.getName());
    }

    public User getUser(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void addAuctionItem(AuctionItem item) {
        auctionItems.add(item);
        System.out.println("Auction item added: " + item.getItemName());
    }

    public AuctionItem getAuctionItem(String itemId) {
        for (AuctionItem item : auctionItems) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    public void placeBid(String userId, String itemId, double bidAmount) throws InvalidBidException {
        User user = getUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }

        AuctionItem item = getAuctionItem(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Auction item not found: " + itemId);
        }

        item.placeBid(user, bidAmount);
    }

    public void displayAllAuctions() {
        System.out.println("\n=== All Auctions ===");
        for (AuctionItem item : auctionItems) {
            System.out.println(item);
        }
    }

    public void displayAuctionDetails(String itemId) {
        AuctionItem item = getAuctionItem(itemId);
        if (item != null) {
            System.out.println("\n=== Auction Details ===");
            System.out.println(item);
            item.displayBidHistory();
        } else {
            System.out.println("Auction item not found: " + itemId);
        }
    }
}
