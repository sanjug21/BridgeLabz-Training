public class FeeDiscount {
    public static void main(String[] args) {
        double fee=125000;
        int disPerc=10;
        double dis=fee/100*disPerc;
        System.out.println("The discount amount is INR "+dis+" and the final discount fee is INR "+(fee-dis));

    }
}
