
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class StockSpan {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of days:");
        int n=sc.nextInt();
        int[] prices=new int[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter price of day "+(i+1)+":");
            prices[i]=sc.nextInt();
        }
        Stack <Integer> s=new Stack<>();
        int[] span=new int[n];
        for(int i=0;i<n;i++){
            while(!s.isEmpty() && prices[i]>prices[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                span[i]=i+1;
            }
            else{
                span[i]=i-s.peek();
            }
            s.push(i);
            
        }
        System.out.println("Stock span for each day is:");
        System.out.println(Arrays.toString(span));
        sc.close();
    }
}
