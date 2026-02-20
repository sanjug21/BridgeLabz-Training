class User {
    String userId;
    String name;
    String email;
    Subscription subscription;
    int loyaltyMonths;

    public User(String userId, String name, String email, Subscription subscription, int loyaltyMonths) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.subscription = subscription;
        this.loyaltyMonths = loyaltyMonths;
    }

    @Override
    public String toString() {
        return userId + " | " + name + " | " + email + " | Loyalty: " + loyaltyMonths + " months";
    }

    public String getDetailedInfo() {
        return toString() + "\n  Subscription: " + subscription;
    }
}
