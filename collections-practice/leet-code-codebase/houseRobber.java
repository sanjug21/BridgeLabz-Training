public class houseRobber {
    // 198. House Robber
    // https://leetcode.com/problems/house-robber/
    public static void main(String[] args) {
        houseRobber obj = new houseRobber();
        int[] nums = { 1, 2, 3, 1 };
        System.out.println(obj.rob(nums));
        System.out.println(obj.robRecursive(nums,0));
    }
    public int rob(int[] nums) {
        int r1 = 0, r2 = 0;
        for (int i : nums) {
            int tmp = Math.max(i + r1, r2);
            r1 = r2;
            r2 = tmp;
        }
        return r2;
    }
    public int robRecursive(int[] nums,int i) {
        if(i >= nums.length){
            return 0;
        }
        int include=nums[i]+robRecursive(nums, i+2);
        int exclude=robRecursive(nums, i+1);
        return Math.max(include,exclude);
    }
}
