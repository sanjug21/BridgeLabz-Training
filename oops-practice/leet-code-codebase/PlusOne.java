import java.util.Arrays;

public class PlusOne {
    // 66. Plus One
    // https://leetcode.com/problems/plus-one/
    public static void main(String[] args) {
        int[] digits={1,2,3};

        PlusOne obj=new PlusOne();
        int[] ans = obj.plusOne(digits);

        System.out.println("Result: " + Arrays.toString(ans));
     
    }

    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]<9){
                digits[i]++;
                return digits;
            }
            digits[i]=0;
        }
        int[] newNumber=new int[digits.length+1];
        newNumber[0]=1;
        return newNumber;
    }
}