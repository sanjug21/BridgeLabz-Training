class Bid {
    private User user;
    private double bidAmount;
    private long timestamp;

    public Bid(User user, double bidAmount) {
        this.user = user;
        this.bidAmount = bidAmount;
        this.timestamp = System.currentTimeMillis();
    }

    public User getUser() {
        return user;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "user='" + user.getName() + '\'' +
                ", bidAmount=" + bidAmount +
                ", timestamp=" + timestamp +
                '}';
    }
}
