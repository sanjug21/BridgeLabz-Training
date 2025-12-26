import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    // 1091. Shortest Path in Binary Matrix
    // https://leetcode.com/problems/shortest-path-in-binary-matrix/
    public static void main(String[] args) {
        ShortestPathInBinaryMatrix obj=new ShortestPathInBinaryMatrix();
        int grid[][]={{0,0,0},{1,1,0},{1,1,0}};
        int ans=obj.shortestPathBinaryMatrix(grid);
        System.out.println("Shortest Path: "+ans);
    }
        public int shortestPathBinaryMatrix(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        if(grid[0][0]!=0 || grid[n-1][m-1]!=0)return -1;
        int[][] dir = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}, 
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{0,0,1});
        while(!q.isEmpty()){
            int curr[]=q.poll();
            int x=curr[0],y=curr[1],d=curr[2];

            if(x==n-1 && y==m-1)return d;

            for(int dr[]:dir){
                int dx=x+dr[0],dy=y+dr[1];
                if(dx>=0 && dx<n && dy>=0 && dy<m && grid[dx][dy]==0){
                    q.add(new int[]{dx,dy,d+1});
                    grid[dx][dy]=1;
                }
            }
        
        }
        return -1;
    }
}
