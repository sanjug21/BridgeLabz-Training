import java.util.Arrays;

public class MoveZeroes {
    // 283. Move Zeroes
    // https://leetcode.com/problems/move-zeroes/
    public static void main(String[] args) {
        int[] nums={0,1,0,3,12};
        MoveZeroes obj=new MoveZeroes();
        obj.moveZeroes(nums);
        System.out.println("Array after moving zeroes: " + Arrays.toString(nums));
        
    }

    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                int temp=nums[lastNonZeroFoundAt];
                nums[lastNonZeroFoundAt]=nums[i];
                nums[i]=temp;
                lastNonZeroFoundAt++;
            }
        }
    }
}