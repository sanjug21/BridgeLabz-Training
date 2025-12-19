import java.util.Scanner;

class palindrome{
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a string to check if it's a palindrome:");
        String s = sc.nextLine();

        int n=s.length();
        int i=0, j=n-1;
        boolean isPalindrome = true;
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                isPalindrome = false;
                break;
            }
            i++;
            j--;
        }
        System.out.println("Is the string a palindrome? " + isPalindrome);
        sc.close();
     }
}