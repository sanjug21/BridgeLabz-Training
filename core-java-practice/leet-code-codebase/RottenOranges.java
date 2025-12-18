import java.util.*;

public class RottenOranges {

    // Leetcode 994. Rotting Oranges
    // https://leetcode.com/problems/rotting-oranges/description/

    public static int n;
    public static int m;
    public static int X[]={0, 0, 1, -1};
    public static int Y[]={1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        int grid[][]=new int[n][m];
        // Input should be only 0 1 or 2
        System.out.println("Enter the grid elements row wise (0:empty, 1:fresh orange, 2:rotten orange):");
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j]=sc.nextInt();
            }
        }
        int ans=orangesRotting(grid);
        System.out.println("Minimum time required to rot all oranges is: "+ans);
        sc.close();

    }

    public static int orangesRotting(int[][] grid){
        Queue<Pair> q=new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2)q.add(new Pair(i,j));
            }
        }

        int ans=0;

        while(!q.isEmpty()){
            boolean fresh=false;
            int size=q.size();

            for(int j=0;j<size;j++){
                Pair p=q.poll();
                int x=p.x,y=p.y;

                for(int i=0;i<4;i++){
                    int dx=x+X[i];
                    int dy=y+Y[i];
                    if(isOut(grid,dx,dy) || grid[dx][dy]!=1 )continue;
                    grid[dx][dy]=2;
                    q.add(new Pair(dx,dy));
                    fresh=true;
                }
            }
            if(fresh)ans++;
        }
        for(int i[]:grid){
            for(int ele:i){
                if(ele==1)ans=-1;
            }
        }
        return ans;
    }
    
    public static boolean isOut(int[][] grid, int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m)return false;
        return true;
    }

    public static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
}
