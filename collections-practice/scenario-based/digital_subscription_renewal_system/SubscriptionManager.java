
import java.util.*;

public class SubscriptionManager {
    private List<User> users;
    private Random random;

    public SubscriptionManager() {
        this.users = new ArrayList<>();
        this.random = new Random();
    }

    // Add user
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.name);
    }

    // Auto-renew active subscriptions
    public void autoRenewSubscriptions() throws PaymentDeclinedException {
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("    AUTO-RENEWAL PROCESS");
        System.out.println("═══════════════════════════════════════════════");

        int renewed = 0;
        int skipped = 0;
        int failed = 0;

        for (User user : users) {
            Subscription sub = user.subscription;

            if (!sub.autoRenew) {
                skipped++;
                continue;
            }

            if (sub.isExpiringSoon() || sub.isExpired()) {
                try {
                    // Simulate payment processing (10% chance of failure)
                    if (random.nextInt(10) == 0) {
                        throw new PaymentDeclinedException(
                            "Payment declined for user " + user.name + " (" + sub.paymentMethod + ")"
                        );
                    }

                    sub.renew(1);
                    user.loyaltyMonths++;
                    System.out.println("Renewed: " + user.name + " (" + sub.planName + ")");
                    renewed++;

                } catch (PaymentDeclinedException e) {
                    System.out.println(e.getMessage());
                    failed++;
                }
            }
        }

        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("Renewed: " + renewed);
        System.out.println("Skipped (Auto-Renew OFF): " + skipped);
        System.out.println("Failed: " + failed);
        System.out.println("═══════════════════════════════════════════════");
    }

    // Identify expired accounts
    public void identifyExpiredAccounts() {
        System.out.println("\n--- Expired Accounts ---");
        List<User> expiredUsers = new ArrayList<>();

        for (User user : users) {
            if (user.subscription.isExpired()) {
                expiredUsers.add(user);
            }
        }

        if (expiredUsers.isEmpty()) {
            System.out.println("No expired accounts found.");
            return;
        }

        System.out.println("Total Expired Accounts: " + expiredUsers.size());
        for (User user : expiredUsers) {
            System.out.println("  " + user.name + " | Expired: " + user.subscription.expiryDate);
        }
    }

    // Identify expiring soon accounts
    public void identifyExpiringSoonAccounts() {
        System.out.println("\n--- Accounts Expiring Soon (within 7 days) ---");
        List<User> expiringSoon = new ArrayList<>();

        for (User user : users) {
            if (user.subscription.isExpiringSoon()) {
                expiringSoon.add(user);
            }
        }

        if (expiringSoon.isEmpty()) {
            System.out.println("No accounts expiring soon.");
            return;
        }

        System.out.println("Total Accounts Expiring Soon: " + expiringSoon.size());
        for (User user : expiringSoon) {
            System.out.println("  " + user.name + " | Expires: " + user.subscription.expiryDate);
        }
    }

    // Renew subscription with discount strategy
    public void renewWithDiscount(String userId, DiscountStrategy discountStrategy, int months)
            throws PaymentDeclinedException {
        User user = findUserById(userId);
        if (user == null) {
            throw new PaymentDeclinedException("User not found: " + userId);
        }

        Subscription sub = user.subscription;
        double basePrice = sub.basePrice * months;
        double discountedPrice = discountStrategy.applyDiscount(basePrice, user.loyaltyMonths);
        double savings = basePrice - discountedPrice;

        System.out.println("\n--- Renewal Summary ---");
        System.out.println("User: " + user.name);
        System.out.println("Plan: " + sub.planName);
        System.out.println("Duration: " + months + " month(s)");
        System.out.println("Base Price: ₹" + basePrice);
        System.out.println("Discount Applied: " + discountStrategy.getDescription());
        System.out.println("Savings: ₹" + savings);
        System.out.println("Final Price: ₹" + discountedPrice);

        // Simulate payment (10% failure rate)
        if (random.nextInt(10) == 0) {
            throw new PaymentDeclinedException("Payment declined for card ending in " + 
                                               sub.paymentMethod.substring(Math.max(0, sub.paymentMethod.length() - 4)));
        }

        sub.renew(months);
        user.loyaltyMonths += months;
        System.out.println("\nSubscription renewed successfully!");
    }

    // Find user by ID
    private User findUserById(String userId) {
        for (User user : users) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // Display all users
    public void displayAllUsers() {
        System.out.println("\n--- All Users ---");
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            System.out.println(user.getDetailedInfo());
        }
    }

    // Display users by subscription status
    public void displayUsersByStatus(String status) {
        System.out.println("\n--- Users with " + status + " Subscriptions ---");
        List<User> filtered = new ArrayList<>();

        for (User user : users) {
            boolean match = false;
            if (status.equalsIgnoreCase("ACTIVE") && !user.subscription.isExpired() && !user.subscription.isExpiringSoon()) {
                match = true;
            } else if (status.equalsIgnoreCase("EXPIRED") && user.subscription.isExpired()) {
                match = true;
            } else if (status.equalsIgnoreCase("EXPIRING") && user.subscription.isExpiringSoon()) {
                match = true;
            }

            if (match) {
                filtered.add(user);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : filtered) {
            System.out.println(user.getDetailedInfo());
        }
    }

    // Get statistics
    public void displayStatistics() {
        System.out.println("\n--- Platform Statistics ---");
        System.out.println("Total Users: " + users.size());

        int active = 0;
        int expired = 0;
        int expiringSoon = 0;
        int autoRenewEnabled = 0;
        double totalRevenue = 0;

        Map<String, Integer> planCount = new HashMap<>();

        for (User user : users) {
            Subscription sub = user.subscription;

            if (sub.isExpired()) {
                expired++;
            } else if (sub.isExpiringSoon()) {
                expiringSoon++;
            } else {
                active++;
            }

            if (sub.autoRenew) {
                autoRenewEnabled++;
            }

            totalRevenue += sub.basePrice;
            planCount.put(sub.planName, planCount.getOrDefault(sub.planName, 0) + 1);
        }

        System.out.println("Active Subscriptions: " + active);
        System.out.println("Expiring Soon: " + expiringSoon);
        System.out.println("Expired: " + expired);
        System.out.println("Auto-Renew Enabled: " + autoRenewEnabled);
        System.out.println("Monthly Revenue: ₹" + totalRevenue);

        System.out.println("\nPlan Distribution:");
        for (Map.Entry<String, Integer> entry : planCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
