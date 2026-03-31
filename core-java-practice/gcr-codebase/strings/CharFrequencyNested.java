import java.util.*;

public class CharFrequencyNested {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter a string:");
        String text=scanner.next();

        String[] result=getFrequencyNestedLoops(text);
        
        System.out.println("Frequencies:");
        for (String s:result) {
            System.out.println(s);
        }
        scanner.close();
    }

    public static String[] getFrequencyNestedLoops(String text) {
        char[] chars=text.toCharArray();
        int[] freq=new int[chars.length];
        
        // Initialize frequencies to 1
        // We will use -1 to mark visited
        
        for(int i=0;i<chars.length;i++) {
            freq[i]=1;
            for(int j=i+1;j<chars.length;j++) {
                if(chars[i]==chars[j]) {
                    freq[i]++;
                    // Mark duplicate to avoid recounting
                    chars[j]='0'; 
                }
            }
        }

        // Count valid entries 
        int count=0;
        for(int i=0;i<chars.length;i++) {
            if(chars[i]!='0'&&chars[i]!=0) count++;
        }

        String[] output=new String[count];
        int idx=0;
        for(int i=0;i<chars.length;i++) {
            if(chars[i]!='0'&&chars[i]!=0) {
                output[idx++]=chars[i]+": "+freq[i];
            }
        }
        return output;
    }
}