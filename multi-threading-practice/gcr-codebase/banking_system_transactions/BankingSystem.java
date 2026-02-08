package banking_system_transactions;
public class BankingSystem {

    public static void main(String[] args) {
        BankAccount account = new BankAccount(10000);

        String[] customers = {"Customer-1", "Customer-2", "Customer-3", "Customer-4", "Customer-5"};
        double[] amounts = {3000, 4000, 2000, 5000, 1500};

        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Transaction(account, customers[i], amounts[i]));
            threads[i].setName(customers[i]);
            System.out.println("Thread " + threads[i].getName() + " state: " + threads[i].getState());
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nFinal Balance: " + (int) account.getBalance());
    }
}
