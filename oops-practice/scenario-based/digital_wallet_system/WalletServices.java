package digital_wallet_system;

interface WalletServices {
    void transferMoney(Wallet sender, Wallet recipient, double amount);
}
