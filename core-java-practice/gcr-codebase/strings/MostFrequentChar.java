import java.util.*;

public class MostFrequentChar {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str=sc.next();
        
        int[] freq=new int[256];
        for(int i=0;i<str.length();i++)freq[str.charAt(i)]++;
        
        char maxChar=' ';
        int maxFreq=0;

        // if the frequency of any character is more than maxFreq, update maxFreq and maxChar
        for(int i=0;i<256;i++){
            if(freq[i]>maxFreq){
                maxFreq=freq[i];
                maxChar=(char)i;
            }
        }
        System.out.println("Most Frequent Character: '"+maxChar+"'");
        sc.close();
    }
}