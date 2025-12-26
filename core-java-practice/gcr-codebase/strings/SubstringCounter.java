import java.util.*;

public class SubstringCounter {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter string: ");
        String str=sc.nextLine();
        System.out.print("Enter substring: ");
        String sub=sc.nextLine();
        
        int count=0;
        int idx=0;
        while((idx=str.indexOf(sub,idx))!=-1){
            count++;
            idx+=sub.length();
        }

        // or we can use startWith Method from each index
        int count2=0;
        for(int i=0;i<=str.length()-sub.length();i++){
            if(str.startsWith(sub,i)){
                count2++;
            }
        }
        System.out.println("Occurrences: "+count);
        System.out.println("Occurrences using method 2: "+count2);
        sc.close();
    }
}