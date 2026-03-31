import java.util.*;
public class TwoSum {
    // 1. Two Sum
    // https://leetcode.com/problems/two-sum/
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum obj = new TwoSum();
        int[] ans = obj.twoSum(nums, target);
        System.out.println("Indices: " + ans[0] + ", " + ans[1]); // Expected: 0, 1
    }

    public int[] twoSum(int[] nums, int target) {
        java.util.HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1}; // Should not reach here for valid inputs
    }

    
}
