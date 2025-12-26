import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    // 417. Pacific Atlantic Water Flow
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
    public static void main(String[] args) {
        int heights[][]={{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        PacificAtlanticWaterFlow obj=new PacificAtlanticWaterFlow();
        List<List<Integer>> ans=obj.pacificAtlantic(heights);
        System.out.println(ans);
    }
    
    int n;
    int m;
    int dir[][]={{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        n=heights.length;
        m=heights[0].length;
        int [][] p=new int[n][m];
        int [][] a=new int[n][m];
        for(int i=0;i<n;i++)dfs(i,0,heights,p);
        for(int i=1;i<m;i++)dfs(0,i,heights,p);
        for(int i=0;i<n;i++)dfs(i,m-1,heights,a);
        for(int i=0;i<m-1;i++)dfs(n-1,i,heights,a);
        // for(int i[]:p)System.out.println(Arrays.toString(i));
        // System.out.println();
        // for(int i[]:a)System.out.println(Arrays.toString(i));
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(p[i][j]==1&& a[i][j]==1){
                    List<Integer> tmp=new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    ans.add(tmp);
                }
            }
        }
        return ans;

    }
    public void dfs(int i,int j,int h[][],int ocean[][]){
        if(isOut(i,j) || ocean[i][j]!=0)return;
        ocean[i][j]=1;
        for(int d[]:dir){
            int x=i+d[0],y=j+d[1];
            if(!isOut(x,y) && h[x][y]>=h[i][j])dfs(x,y,h,ocean);
        }

    }
    public boolean isOut(int i,int j){
        return i<0 || i>=n || j<0 || j>=m;
    }
}
