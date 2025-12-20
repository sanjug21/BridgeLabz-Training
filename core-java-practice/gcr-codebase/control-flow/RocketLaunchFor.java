import java.util.Scanner;

public class RocketLaunchFor {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        for(int counter=number;counter>=1;counter--){
            System.out.println(counter);
        }
        sc.close();
    }
}
