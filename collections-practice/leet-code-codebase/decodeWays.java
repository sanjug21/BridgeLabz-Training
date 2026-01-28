public class decodeWays {
    // 91. Decode Ways
    // https://leetcode.com/problems/decode-ways/
    public static void main(String[] args) {
        decodeWays solution = new decodeWays();
        String s = "226";
        int result = solution.numDecodings(s);
        System.out.println(result);
        int result2 = solution.numDecodings2(s);
        System.out.println(result2);
        int result3 = solution.numDecodings3(s);
        System.out.println(result3);

    }

    // dp[n+1]
    // space O(n)
    // time O(n)
    public int numDecodings(String s) {
        int n = s.length();
        int dp[] = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == '0')
                continue;
            else
                dp[i] += dp[i + 1];
            if (i + 1 < n && (ch == '1' || (ch == '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6')))
                dp[i] += dp[i + 2];
        }
        return dp[0];
    }

    // more optimized
    // space O(1)
    // time O(n)
    public int numDecodings2(String s) {
        int n = s.length();
        int next = 1;
        int nextNext = 0;
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            int current = 0;
            if (ch == '0') {
                current = 0;
            } else {
                current += next;
                if (i + 1 < n && (ch == '1' || (ch == '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6')))
                    current += nextNext;
            }
            nextNext = next;
            next = current;
        }
        return next;

    }

    // brute force
    // space O(n)
    // time O(2^n)
    public int numDecodings3(String s) {
        return helper(s, 0);
    }

    public int helper(String s, int i) {
        if (i == s.length())
            return 1;
        if (s.charAt(i) == '0')
            return 0;
        int result = helper(s, i + 1);
        if (i + 1 < s.length()
                && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6')))
            result += helper(s, i + 2);
        return result;
    }
}