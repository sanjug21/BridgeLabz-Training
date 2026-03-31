import java.util.*;
public class balancedMaxSubstring {
    // calculate the length of the longest balanced substring
    // balnced substring means equal number of 0s and 1s
    // you have k number of flips allowed to flip 0 to 1 or 1 to 0

    
    public static void main(String[] args) {
        balancedMaxSubstring solution = new balancedMaxSubstring();
        String s = "00000000000001";
        int k = 2;
        int result = solution.maxSubstring(s, k);
        System.out.println(result);
    }
    public int maxSubstring(String s, int k) {
        int n = s.length();
        int maxLength = 0;
        
        // Convert string to prefix sum: 0 -> -1, 1 -> +1
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '0' ? -1 : 1);
        }
        
        // HashMap to store earliest occurrence of each prefix sum
        // Key: prefix sum, Value: index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        
        for (int j = 1; j <= n; j++) {
            int currentSum = prefixSum[j];
            
            // Check all valid prefix sums in range [currentSum - 2k, currentSum + 2k]
            for (int targetSum = currentSum - 2 * k; targetSum <= currentSum + 2 * k; targetSum++) {
                if (map.containsKey(targetSum)) {
                    int i = map.get(targetSum);
                    int length = j - i;
                    
                    // Check if length is even
                    if (length % 2 == 0) {
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }
            
            // Store only the earliest occurrence of each prefix sum
            if (!map.containsKey(currentSum)) {
                map.put(currentSum, j);
            }
        }
        
        return maxLength;
    }
}
