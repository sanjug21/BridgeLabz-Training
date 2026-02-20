import java.time.LocalDate;
import java.util.Scanner;

public class SubscriptionSystem {

    public static void main(String[] args) {
        SubscriptionManager manager = new SubscriptionManager();
        Scanner sc = new Scanner(System.in);

        // Initialize demo data
        initializeDemoData(manager);

        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║ DIGITAL SUBSCRIPTION RENEWAL SYSTEM    ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.println("1. Add User");
            System.out.println("2. Auto-Renew All Subscriptions");
            System.out.println("3. Renew Subscription with Discount");
            System.out.println("4. View Expired Accounts");
            System.out.println("5. View Expiring Soon Accounts");
            System.out.println("6. View All Users");
            System.out.println("7. View Users by Status");
            System.out.println("8. Display Statistics");
            System.out.println("9. Exit");
            System.out.print("\nChoose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        addUser(manager, sc);
                        break;
                    case 2:
                        manager.autoRenewSubscriptions();
                        break;
                    case 3:
                        renewWithDiscount(manager, sc);
                        break;
                    case 4:
                        manager.identifyExpiredAccounts();
                        break;
                    case 5:
                        manager.identifyExpiringSoonAccounts();
                        break;
                    case 6:
                        manager.displayAllUsers();
                        break;
                    case 7:
                        viewUsersByStatus(manager, sc);
                        break;
                    case 8:
                        manager.displayStatistics();
                        break;
                    case 9:
                        System.out.println("Exiting system. Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (PaymentDeclinedException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void initializeDemoData(SubscriptionManager manager) {
        // Create users with various subscription states
        
        // Active subscription
        manager.addUser(new User("U001", "Rajesh Kumar", "rajesh@email.com",
            new Subscription("Premium", 299, LocalDate.now().minusMonths(2), 6, true, "Credit Card"),
            24));

        // Expiring soon (within 7 days)
        manager.addUser(new User("U002", "Priya Sharma", "priya@email.com",
            new Subscription("Basic", 199, LocalDate.now().minusDays(25), 1, true, "Debit Card"),
            6));

        // Expired
        manager.addUser(new User("U003", "Amit Singh", "amit@email.com",
            new Subscription("Standard", 249, LocalDate.now().minusMonths(2), 1, false, "UPI"),
            12));

        // Active, no auto-renew
        manager.addUser(new User("U004", "Sneha Patel", "sneha@email.com",
            new Subscription("Premium", 299, LocalDate.now().minusDays(10), 3, false, "Net Banking"),
            36));

        // Expiring soon with auto-renew
        manager.addUser(new User("U005", "Vikram Reddy", "vikram@email.com",
            new Subscription("Basic", 199, LocalDate.now().minusDays(27), 1, true, "Credit Card"),
            3));

        // Long-term active user
        manager.addUser(new User("U006", "Anjali Gupta", "anjali@email.com",
            new Subscription("Premium", 299, LocalDate.now().minusMonths(1), 12, true, "Credit Card"),
            48));

        System.out.println("\nDemo data initialized successfully!");
        System.out.println("  6 users added with various subscription states");
    }

    private static void addUser(SubscriptionManager manager, Scanner sc) {
        System.out.print("\nEnter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.println("\nSelect Plan:");
        System.out.println("1. Basic (₹199/month)");
        System.out.println("2. Standard (₹249/month)");
        System.out.println("3. Premium (₹299/month)");
        System.out.print("Choice: ");
        int planChoice = sc.nextInt();

        String planName = "";
        double price = 0;
        switch (planChoice) {
            case 1:
                planName = "Basic";
                price = 199;
                break;
            case 2:
                planName = "Standard";
                price = 249;
                break;
            case 3:
                planName = "Premium";
                price = 299;
                break;
            default:
                System.out.println("Invalid plan choice.");
                return;
        }

        System.out.print("Duration (months): ");
        int months = sc.nextInt();

        System.out.print("Enable Auto-Renew (true/false): ");
        boolean autoRenew = sc.nextBoolean();
        sc.nextLine();

        System.out.print("Payment Method: ");
        String paymentMethod = sc.nextLine();

        System.out.print("Loyalty Months: ");
        int loyaltyMonths = sc.nextInt();
        sc.nextLine();

        Subscription subscription = new Subscription(planName, price, LocalDate.now(), 
                                                     months, autoRenew, paymentMethod);
        User user = new User(userId, name, email, subscription, loyaltyMonths);
        manager.addUser(user);
    }

    private static void renewWithDiscount(SubscriptionManager manager, Scanner sc)
            throws PaymentDeclinedException {
        System.out.print("\nEnter User ID: ");
        String userId = sc.nextLine();

        System.out.print("Renewal Duration (months): ");
        int months = sc.nextInt();
        sc.nextLine();

        System.out.println("\nSelect Discount Strategy:");
        System.out.println("1. No Discount");
        System.out.println("2. Student Discount (30% OFF)");
        System.out.println("3. Senior Citizen Discount (40% OFF)");
        System.out.println("4. Loyalty Discount (based on tenure)");
        System.out.println("5. Seasonal Discount (20% OFF)");
        System.out.print("Choice: ");
        int discountChoice = sc.nextInt();
        sc.nextLine();

        DiscountStrategy strategy;
        switch (discountChoice) {
            case 1:
                strategy = new NoDiscountStrategy();
                break;
            case 2:
                strategy = new StudentDiscountStrategy();
                break;
            case 3:
                strategy = new SeniorCitizenDiscountStrategy();
                break;
            case 4:
                strategy = new LoyaltyDiscountStrategy();
                break;
            case 5:
                strategy = new SeasonalDiscountStrategy();
                break;
            default:
                System.out.println("Invalid discount choice.");
                return;
        }

        manager.renewWithDiscount(userId, strategy, months);
    }

    private static void viewUsersByStatus(SubscriptionManager manager, Scanner sc) {
        System.out.println("\nSelect Status:");
        System.out.println("1. Active");
        System.out.println("2. Expired");
        System.out.println("3. Expiring Soon");
        System.out.print("Choice: ");
        int statusChoice = sc.nextInt();
        sc.nextLine();

        String status = "";
        switch (statusChoice) {
            case 1:
                status = "ACTIVE";
                break;
            case 2:
                status = "EXPIRED";
                break;
            case 3:
                status = "EXPIRING";
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        manager.displayUsersByStatus(status);
    }
}
