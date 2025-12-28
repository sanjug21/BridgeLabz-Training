import java.util.*;

public class MaxFinder {
    public static void main(String[] args) {
        int[] nums=getInput();
        int max=findMax(nums[0],nums[1],nums[2]);
        System.out.println("Maximum: "+max);
    }

    static int[] getInput(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter 3 integers: ");
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        int num3=sc.nextInt();
        sc.close();
        return new int[]{num1,num2,num3};
    }

    static int findMax(int num1,int num2,int num3){
        int m=num1;
        if(num2>m) m=num2;
        if(num3>m) m=num3;
        return m;
    }
}