public class ProfitLoss {
    public static void main(String[] args) {
        int cp=129;
        int sp=191;
        int profit=Math.abs(sp-cp);
        int per=(profit*100/cp);
        System.err.println("Cost Price is INR "+cp+" and Selling Price is INR "+sp);
        System.out.println("The profit is INR "+profit+" and the Profit Percentage is "+per);
    }
}
