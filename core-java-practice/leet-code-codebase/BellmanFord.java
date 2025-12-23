import java.util.Arrays;

public class BellmanFord {
    public static void main(String[] args) {
        BellmanFord obj=new BellmanFord();
        int edges[][]={{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}};
        int V=4,src=0;
        int ans[]=obj.bellmanFord(V,edges,src);
        System.out.println(Arrays.toString(ans));
    }
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int ans[]=new int[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[src]=0;
        // Relaxation step
        for(int i=0;i<V-1;i++){
            for(int[] edge:edges){
                int u=edge[0];
                int v=edge[1];
                int w=edge[2];
                if(ans[u]!=Integer.MAX_VALUE && ans[u]+w<ans[v]){
                    ans[v]=ans[u]+w;
                }
            }
        }
        return ans;
    }
}