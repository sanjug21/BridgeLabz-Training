import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Invoice {
    private String invoiceId;
    private String transactionId;
    private LocalDate invoiceDate;

    public Invoice(String transactionId) {
        this.transactionId = transactionId;
        this.invoiceId = "INV-" + transactionId;
        this.invoiceDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format("Invoice[ID=%s, TxnID=%s, Date=%s]",
                invoiceId, transactionId, invoiceDate);
    }
}

public class InvoiceObjectCreation {

    public static void main(String[] args) {
        List<String> ids = Arrays.asList("TXN001", "TXN002", "TXN003");

        List<Invoice> invoices = ids.stream()
                .map(Invoice::new)
                .collect(Collectors.toList());

        invoices.forEach(System.out::println);
    }
}
