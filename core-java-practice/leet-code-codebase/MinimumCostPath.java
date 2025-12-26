import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostPath {
    // https://www.geeksforgeeks.org/problems/minimum-cost-path3833/1
    // Minimum Cost Path
    public static void main(String[] args) {
        MinimumCostPath obj=new MinimumCostPath();
        int grid[][]={{5,9,6},{11,5,2},{4,7,1}};
        int ans=obj.minimumCostPath(grid);
        System.out.println("Minimum Cost Path: "+ans);
    }
    public int minimumCostPath(int[][] grid) {
        // Code here
        int n=grid.length;
        int m=grid[0].length;
        int[][] dir = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}, 
        };
        int dp[][]=new int[n][m];
        for(int i[]:dp)Arrays.fill(i,Integer.MAX_VALUE);
        dp[0][0]=grid[0][0];
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(a[2]-b[2]));
        pq.add(new int []{0,0,grid[0][0]});
        while(!pq.isEmpty()){
            int c[]=pq.poll();
            int i=c[0],j=c[1],w=c[2];
            
            if(i==n-1 && j==m-1)return w;
            if (w > dp[i][j]) continue;

            for(int d[]:dir){
                int x=i+d[0],y=j+d[1];
                
                if(x>=0 && x<n && y>=0 && y<m && grid[x][y]+w<dp[x][y] ){
                    dp[x][y]=grid[x][y]+w;
                    pq.add(new int[]{x,y,grid[x][y]+w});
                }
            }
        }
        
        return -1;
    }
}
