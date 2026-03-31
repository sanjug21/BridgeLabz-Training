public class SlidingWindowMaximum {
    // 239. Sliding Window Maximum
    // https://leetcode.com/problems/sliding-window-maximum/
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int[] ans = obj.maxSlidingWindow(nums, k);
        for (int i : ans) {
            System.out.print(i + " ");
        }
        System.out.println(); // Expected: 3 3 5 5 6 7

        int[] nums2 = {1};
        k = 1;
        int[] ans2 = obj.maxSlidingWindow(nums2, k);
        for (int i : ans2) {
            System.out.print(i + " ");
        }
        System.out.println(); // Expected: 1
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        int resultIdx = 0;

        // Deque to store indices of elements
        // The front of the deque will always store the index of the maximum element in the current window
        java.util.Deque<Integer> dq = new java.util.LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.removeFirst();
            }

            // Remove elements from the back of the deque that are smaller than the current element
            // because they can no longer be the maximum in any future window
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }

            // Add the current element's index to the back of the deque
            dq.addLast(i);

            // The front of the deque is the maximum element for the current window
            if (i >= k - 1) {
                result[resultIdx++] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
    
}
