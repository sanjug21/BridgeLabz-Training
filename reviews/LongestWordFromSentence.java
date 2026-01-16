import java.util.*;
public class LongestWordFromSentence {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String words[]=sc.nextLine().trim().split(" ");
        int maxLength=0;
        String LongestWord="";
        for(String s:words){
            if(s.length()>maxLength){
                maxLength=s.length();
                LongestWord=s;
            }
        }
        System.out.println(LongestWord);
        System.out.println("Length of the word"+LongestWord+" is: "+maxLength);
    }
}
