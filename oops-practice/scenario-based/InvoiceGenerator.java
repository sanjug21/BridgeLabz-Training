import java.util.Scanner;

public class InvoiceGenerator {

    String input;
    public InvoiceGenerator(String input) {
        this.input=input;
    }


    // Method to split the input string into individual task strings
    public  String[] parseInvoice() {
        // Split by comma followed by optional whitespace
        return input.split(",\\s*");
    }

    // Method to calculate the total amount from the array of task strings
    public  double getTotalAmount() {
        String[] tasks = parseInvoice();
        double total = 0.0;
        for (String task : tasks) {
            // Expected format: "Task Name - Amount INR"
            // We split by " - " to separate name and amount
            String[] parts = task.split(" - ");
            if (parts.length == 2) {
                // parts[1] is like "3000 INR"
                // We split by space to get the number
                String[] amountParts = parts[1].trim().split(" ");
                if (amountParts.length > 0) {
                    try {
                        total += Double.parseDouble(amountParts[0]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount format in: " + task);
                    }
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String input = "Logo Design - 3000 INR, Web Page - 4500 INR";
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(input);
        double totalAmount = invoiceGenerator.getTotalAmount();
        System.out.println("Total 1st Invoice Amount: " + totalAmount + " INR");

        Scanner sc=new Scanner(System.in);
        // input need to be in proper format other wise it will throw an error
        System.out.println("Enter details for 2nd invoice and it should be in proper format other wise you can get unexpected result or error ");
        String input2=sc.nextLine();
        InvoiceGenerator invoiceGenerator2 = new InvoiceGenerator(input2);
        double totalAmount2 = invoiceGenerator2.getTotalAmount();
        System.out.println("Total 2nd Invoice Amount: " + totalAmount2 + " INR");
        
        
        
         sc.close();
    }
}