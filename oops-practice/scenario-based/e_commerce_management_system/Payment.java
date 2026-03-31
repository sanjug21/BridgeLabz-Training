package e_commerce_management_system;

interface Payment {
    void payUsingUPI(String UPIId, double amount);
    void payUsingCard(String cardNumber, String expiryDate, String cvv, double amount);
    void payUsingWallet(String walletId, double amount);
}