public class FeeDiscount {
    public static void main(String[] args) {
        double fee=125000;
        int discountPercentage=10;
        double discount=fee/100*discountPercentage;
        System.out.println("The discount amount is INR "+discount+" and the final discount fee is INR "+(fee-discount));

    }
}
