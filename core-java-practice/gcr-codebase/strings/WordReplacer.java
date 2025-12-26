import java.util.*;

public class WordReplacer {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter sentence: ");
        String sentence=sc.nextLine();
        System.out.print("Enter word to replace: ");
        String target=sc.next();
        System.out.print("Enter replacement word: ");
        String replacement=sc.next();
        
        String res=replaceWord(sentence,target,replacement);
        System.out.println("Modified sentence: "+res);
        sc.close();
    }
    
    public static String replaceWord(String sentence,String target,String replacement){
        // Split the sentence into words using split method
        String[] words=sentence.split(" ");
        String res="";
        for(int i=0;i<words.length;i++){
            if(words[i].equals(target))res+=replacement;
            else res+=words[i];
            if(i<words.length-1)res+=" ";
        }
        return res;
    }
}