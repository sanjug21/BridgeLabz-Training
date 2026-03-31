public class climbingStairs {
    // 70. Climbing Stairs
    // https://leetcode.com/problems/climbing-stairs/
    public static void main(String[] args) {
        climbingStairs obj = new climbingStairs();
        System.out.println(obj.climbStairs(2));
        System.out.println(obj.climbStairs(3));
    }

    public int climbStairs(int n) {
        if (n <= 3)
            return n;
        int a = 2, b = 3;
        for (int i = 4; i <= n; i++) {
            int tmp = a + b;
            a = b;
            b = tmp;
        }
        return b;
    }

}
