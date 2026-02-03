import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoggingTransactions {

    public static void main(String[] args) {
        List<String> transactionIds = new ArrayList<>();
        transactionIds.add("TXN-1001");
        transactionIds.add("TXN-1002");
        transactionIds.add("TXN-1003");
        transactionIds.add("TXN-1004");
        transactionIds.add("TXN-1005");

        System.out.println("Transaction Log:");
        transactionIds.forEach(id -> System.out.println(LocalDateTime.now() + " - Transaction: " + id));
    }
}
