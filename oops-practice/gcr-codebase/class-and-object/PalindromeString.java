public class PalindromeString {
    String text;

    public PalindromeString(String text){
        this.text=text;
    }

    public boolean isPalindrome(){
        String clean=text.trim().replaceAll("\\s+", "").toLowerCase();
        int length=clean.length();
        int start=0;
        int end=length-1;
        while(start<end){
            if(clean.charAt(start)!=clean.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public void display(){
        if(isPalindrome()){
            System.out.println(text+" is palindrome");
        }else{
            System.out.println(text+" is not Palindrome");
        }
    }

    public static void main(String[] args){
        PalindromeString p1=new PalindromeString("A man a plan a canal Panama");
        p1.display();
        PalindromeString p2=new PalindromeString("Hello");
        p2.display();
    }
}