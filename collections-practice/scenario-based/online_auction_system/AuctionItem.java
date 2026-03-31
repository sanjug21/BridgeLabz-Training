import java.util.TreeMap;
import java.util.Comparator;

class AuctionItem {
    private String itemId;
    private String itemName;
    private double startingPrice;
    private TreeMap<Double, User> bidMap;
    private boolean auctionActive;

    public AuctionItem(String itemId, String itemName, double startingPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.bidMap = new TreeMap<>(Comparator.reverseOrder());
        this.auctionActive = true;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getHighestBid() {
        if (bidMap.isEmpty()) {
            return startingPrice;
        }
        return bidMap.firstKey();
    }

    public User getHighestBidder() {
        if (bidMap.isEmpty()) {
            return null;
        }
        return bidMap.get(bidMap.firstKey());
    }

    public void placeBid(User user, double bidAmount) throws InvalidBidException {
        if (!auctionActive) {
            throw new InvalidBidException("Auction is closed for item: " + itemName);
        }

        if (bidAmount <= getHighestBid()) {
            throw new InvalidBidException(
                    "Bid amount must be higher than current highest bid of " + getHighestBid() +
                    ". Your bid: " + bidAmount);
        }

        if (user.getWalletBalance() < bidAmount) {
            throw new InvalidBidException(
                    "Insufficient balance. Available: " + user.getWalletBalance() +
                    ", Required: " + bidAmount);
        }

        bidMap.put(bidAmount, user);
        System.out.println("Bid placed successfully: " + user.getName() + " bid " + bidAmount);
    }

    public void closeAuction() {
        auctionActive = false;
        System.out.println("Auction closed for item: " + itemName);
    }

    public void displayBidHistory() {
        System.out.println("\n=== Bid History for " + itemName + " ===");
        if (bidMap.isEmpty()) {
            System.out.println("No bids placed. Starting price: " + startingPrice);
            return;
        }

        int rank = 1;
        for (Double bid : bidMap.keySet()) {
            System.out.println(rank + ". " + bidMap.get(bid).getName() + " - " + bid);
            rank++;
        }
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", startingPrice=" + startingPrice +
                ", highestBid=" + getHighestBid() +
                ", highestBidder=" + (getHighestBidder() != null ? getHighestBidder().getName() : "None") +
                '}';
    }
}
