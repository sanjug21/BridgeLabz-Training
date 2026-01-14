
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TargetPair {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter element "+(i+1)+":");
            arr[i]=sc.nextInt();

        }
        System.out.println("Enter the target");
        int target=sc.nextInt();
        Map<Integer,Integer>map=new HashMap<>();

        for(int i=0;i<n;i++){
            int rem=target-arr[i];
            if(map.containsKey(rem)){
                // print all pairs
                System.out.println("Pair found: ("+rem+","+arr[i]+")");

            }
            map.put(arr[i],i);
        }
        sc.close();
    }
}
