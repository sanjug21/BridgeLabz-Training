import java.util.Scanner;

public class MaxAreaOfIslands {
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        int maxArea = maxAreaOfIsland(grid);
        System.out.println("Maximum Area of Island: " + maxArea);
        sc.close();
    }

    public static int maxAreaOfIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(i, j, grid);
                    ans = Math.max(ans, area);

                }
            }
        }
        return ans;
    }

    public static int dfs(int i, int j, int[][] grid) {
        if (isOut(i, j) || grid[i][j] == 0)
            return 0;
        int ans = 1;
        grid[i][j] = 0;
        ans += dfs(i + 1, j, grid);
        ans += dfs(i - 1, j, grid);
        ans += dfs(i, j + 1, grid);
        ans += dfs(i, j - 1, grid);
        return ans;
    }

    public static boolean isOut(int i, int j) {
        return i < 0 || i >= n || j < 0 || j >= m;
    }
}
