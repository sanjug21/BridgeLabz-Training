public class ProfitLoss {
    public static void main(String[] args) {
        int costPrice=129;
        int sellingPrice=191;
        int profit=Math.abs(sellingPrice-costPrice);
        int per=(profit*100/costPrice);
        System.err.println("Cost Price is INR "+costPrice+" and Selling Price is INR "+sellingPrice);
        System.out.println("The profit is INR "+profit+" and the Profit Percentage is "+per);
    }
}
