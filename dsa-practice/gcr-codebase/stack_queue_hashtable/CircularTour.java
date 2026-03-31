
import java.util.Scanner;

public class CircularTour {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of petrol pumps:");
        int n=sc.nextInt();
        int petrol[]=new int[n];
        int distance[]=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter petrol in pump "+(i+1)+":");
            petrol[i]=sc.nextInt();
            System.out.println("Enter distance from current pump to next pump:");
            distance[i]=sc.nextInt();
        }
        int start=0;
        int curr_petrol=0;
        int total_petrol=0;
        for(int i=0;i<n;i++){
            int net=petrol[i]-distance[i];
            total_petrol+=net;
            curr_petrol+=net;
            if(curr_petrol<0){
                curr_petrol=0;
                start=i+1;
            }
        }
        if(total_petrol<0){
            System.out.println("No solution");
        }
        else{
            System.out.println("Start from pump "+(start+1));
        }
        sc.close();
    }
    
}
