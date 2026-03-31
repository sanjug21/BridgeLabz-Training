
public class MaximumSubarray {
    // 53. Maximum Subarray
    // https://leetcode.com/problems/maximum-subarray/
    public static void main(String[] args) {
        int[] nums={-2,1,-3,4,-1,2,1,-5,4};

        MaximumSubarray obj=new MaximumSubarray();
        int ans = obj.maxSubArray(nums);
        System.out.println("Maximum Subarray Sum: " + ans);
     
    }

    public int maxSubArray(int[] nums) {
        int ans=nums[0];
        int max=nums[0];
        for(int i=1;i<nums.length;i++){
            max+=nums[i];
            ans=Math.max(ans,max);
            if(max<0)max=0;
        }
        return ans;
    }
}