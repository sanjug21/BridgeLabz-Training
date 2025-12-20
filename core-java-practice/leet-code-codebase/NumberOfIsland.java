import java.util.Scanner;

public class NumberOfIsland {

        // 200. Number of Islands
        // https://leetcode.com/problems/number-of-islands/
    
        static int n;
        static int m;
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int m=sc.nextInt();
            char[][] grid=new char[n][m];
            for(int i=0;i<n;i++){
                String s=sc.next();
                for(int j=0;j<m;j++){
                    grid[i][j]=s.charAt(j);
                }
            }
            int NumberOfIsland=numIslands(grid);
            System.out.println("Number of Islands: "+NumberOfIsland);
            sc.close();
        }

        public static int numIslands(char[][] grid) {
            n = grid.length;
            m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        dfs(i, j, grid);
                        ans++;
                    }
                }
            }
            return ans;
        }

        public static void dfs(int i, int j, char[][] grid) {
            if (isOut(i, j) || grid[i][j] == '0')
                return;
            grid[i][j] = '0';
            dfs(i + 1, j, grid);
            dfs(i - 1, j, grid);
            dfs(i, j + 1, grid);
            dfs(i, j - 1, grid);
        }

        public static boolean isOut(int i, int j) {
            return i < 0 || i >= n || j < 0 || j >= m;
        }
    }
