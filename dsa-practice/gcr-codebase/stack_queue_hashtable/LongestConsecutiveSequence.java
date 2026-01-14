
import java.util.HashSet;

public class LongestConsecutiveSequence {
    // Problem: Longest Consecutive Sequence
    // Hint: Use a hash map (or set) to store elements and check for consecutive elements efficiently.

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Original Array: 100, 4, 200, 1, 3, 2");
        System.out.println("Length of longest consecutive sequence: " + longestConsecutive(nums)); // Expected: 4 (1, 2, 3, 4)
    }

    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        for (int num : set) {
            // Only check if it's the start of a sequence (left neighbor doesn't exist)
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}