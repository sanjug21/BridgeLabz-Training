import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    // 934. Shortest Bridge
    // https://leetcode.com/problems/shortest-bridge/

    static int n;
    static int X[] = { 0, 0, 1, -1 };
    static int Y[] = { 1, -1, 0, 0 };
    static Queue<int[]> q;

    public static void main(String[] args) {
        int grid[][] = { { 0, 1, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
        int res =shortestBridge(grid);
        System.out.println("Shortest Bridge Length: " + res);
        
    }
    
    public static int shortestBridge(int[][] grid) {
        n=grid.length;
        q=new LinkedList<>();
        island(grid);
        int ans=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr[]=q.poll();
                int x=curr[0],y=curr[1];
                for(int j=0;j<4;j++){
                    int dx=x+X[j],dy=y+Y[j];
                    if(isOut(dx,dy))continue;
                    if(grid[dx][dy]==1)return ans;
                    if(grid[dx][dy]==0){
                        q.add(new int[]{dx,dy});
                        grid[dx][dy]=-1;
                    }

                }
            }
            ans++;
        }
        return 0;
    }
    public static void island(int[][] grid){
         for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    dfs(i,j,grid);
                    return;
                }
            }
        }
    }
    public static void dfs(int i,int j,int[][] grid){
        if(isOut(i,j) || grid[i][j]!=1)return;
        q.add(new int[]{i,j});
        grid[i][j]=-1;
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }
    public static boolean isOut(int i,int j){
        return i<0 || j<0 ||j==n || i==n;
    }
    
}
