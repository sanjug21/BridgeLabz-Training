import java.util.Scanner;

public class CMtoFeet {
    public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      double cm=sc.nextDouble();
      double totalInches=cm/2.54;
      double feet=totalInches/12;
      double inch=totalInches%12;
      System.out.println("Your Height in cm is "+cm+" while in feet is "+feet+" and inches is "+inch);
      sc.close();
    }
}
