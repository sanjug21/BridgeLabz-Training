import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {
    // 239. Sliding Window Maximum
    // https://leetcode.com/problems/sliding-window-maximum/
    public static void main(String[] args) {
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };  
        int k=3;
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int[] ans = obj.maxSlidingWindow(nums, k);
        System.out.println("Sliding Window Maximum\n" + Arrays.toString(ans));
        
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int ans[] = new int[n - k + 1];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!q.isEmpty() && q.peekFirst() < i - k + 1)
                q.pollFirst();
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[q.peekFirst()];
            }
        }
        return ans;

    }
}
