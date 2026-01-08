
public class SingleNumber {
    // 136. Single Number
    // https://leetcode.com/problems/single-number/
    public static void main(String[] args) {
        int nums[]={4,1,2,1,2};
        SingleNumber obj=new SingleNumber();
        int ans = obj.singleNumber(nums);
        System.out.println("Single Number: " + ans);
        
    }

    public int singleNumber(int[] nums) {
        int ans=0;
        for(int num:nums){
            ans^=num;
        }
        return ans;
    }
}