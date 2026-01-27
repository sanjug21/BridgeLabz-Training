public class minCosClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        minCosClimbingStairs obj=new minCosClimbingStairs();
        System.out.println("Minimum cost to climb stairs: " + obj.minCostClimbingStairs(cost));
    }
    // using dynamic programming to store the minimum cost to reach each step
    // time complexity: O(n)
    // space complexity: O(n)
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];

    }
    // optimized space complexity to O(1)
    public int minCostClimbingStairsOptimized(int[] cost) {
        int n = cost.length;
        int first = 0;
        int second = 0;
        for (int i = 2; i <= n; i++) {
            int current = Math.min(second + cost[i - 1], first + cost[i - 2]);
            first = second;
            second = current;
        }
        return second;
    }

    // brute force using recursion
    // time complexity: O(2^n)
    // space complexity: O(n)
    public int minCostClimbingStairsBruteForce(int[] cost) {
        // min cost to reach the top can be from either the last step or the second last step
        return Math.min(climb(cost, cost.length - 1), climb(cost, cost.length - 2));

    }
    private int climb(int[] cost, int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return cost[n];
        }
        return cost[n] + Math.min(climb(cost, n - 1), climb(cost, n - 2));
    }

}
