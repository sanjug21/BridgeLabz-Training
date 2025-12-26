import java.util.Scanner;

public class FriendsComparison {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] age=new int[3];
        double[] height=new double[3];
        String[] names={"Amar", "Akbar", "Anthony"};

        // Enters for age and height
        for(int i=0;i<3;i++){
            System.out.print("Enter age for "+names[i]+": ");
            age[i]=sc.nextInt();
            System.out.print("Enter height for "+names[i]+": ");
            height[i]=sc.nextDouble();
        }

        int minAge=age[0];
        int youngIndex=0;
        double maxHeight=height[0];
        int tallIndex=0;

        // Loop to find youngest and tallest
        for(int i=1;i<3;i++){
            if(age[i]<minAge){
                minAge=age[i];
                youngIndex=i;
            }
            if(height[i]>maxHeight){
                maxHeight=height[i];
                tallIndex=i;
            }
        }

        System.out.println("Youngest friend is: "+names[youngIndex]);
        System.out.println("Tallest friend is: "+names[tallIndex]);
        sc.close();
    }
}