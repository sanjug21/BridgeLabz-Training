import java.util.Scanner;

public class SimpleIntrest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int principle=sc.nextInt();
        int rate=sc.nextInt();
        int time=sc.nextInt();
        double SimpleIntrest=(principle*rate*time)/100;
        System.out.println(SimpleIntrest);
        sc.close();
    }
}
