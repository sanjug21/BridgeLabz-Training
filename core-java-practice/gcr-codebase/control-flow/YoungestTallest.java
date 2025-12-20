import java.util.Scanner;

public class YoungestTallest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int amarAge=sc.nextInt();
        int amarHeight=sc.nextInt();
        int akbarAge=sc.nextInt();
        int akbarHeight=sc.nextInt();
        int anthonyAge=sc.nextInt();
        int anthonyHeight=sc.nextInt();

        if(amarAge<akbarAge && amarAge<anthonyAge){
            System.out.println("Youngest friend is Amar");
        } else if(akbarAge<amarAge && akbarAge<anthonyAge){
            System.out.println("Youngest friend is Akbar");
        } else {
            System.out.println("Youngest friend is Anthony");
        }

        if(amarHeight>akbarHeight && amarHeight>anthonyHeight){
            System.out.println("Tallest friend is Amar");
        } else if(akbarHeight>amarHeight && akbarHeight>anthonyHeight){
            System.out.println("Tallest friend is Akbar");
        } else {
            System.out.println("Tallest friend is Anthony");
        }
        sc.close();
    }
}