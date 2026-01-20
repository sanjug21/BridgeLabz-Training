import java.util.Stack;


public class ValidParentheses {
    // 20. Valid Parentheses
    // https://leetcode.com/problems/valid-parentheses/

    public static void main(String[] args) {
        
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c=='(' || c=='{' || c=='['){
                stack.push(c);
            }
            else{
                if(stack.isEmpty())return false;
                char top=stack.pop();
                if((c==')' && top!='(') || (c=='}' && top!='{') || (c==']' && top!='['))return false;
            }
        }
        return stack.isEmpty();   

    }
}