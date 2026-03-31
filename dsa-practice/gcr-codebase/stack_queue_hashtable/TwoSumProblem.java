
import java.util.HashMap;
import java.util.Scanner;

public class TwoSumProblem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter element "+(i+1)+":");
            arr[i]=sc.nextInt();
        }
        System.out.println("Enter the target sum:");
        int target=sc.nextInt();
        sc.close();
        HashMap <Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            int comp=target-arr[i];
            if(map.containsKey(comp)){
                System.out.println("Pair found: ("+i+","+map.get(comp)+")");
                return;
            }
            map.put(arr[i],i);
        }
        System.out.println("No pair found");
      
    }
}
