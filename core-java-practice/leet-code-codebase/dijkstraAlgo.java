import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class dijkstraAlgo {
    public static void main(String[] args) {
        dijkstraAlgo obj=new dijkstraAlgo();
        int edges[][]={{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}};
        int V=4,src=0;
        int ans[]=obj.dijkstra(V,edges,src);
        System.out.println(Arrays.toString(ans));

    }
    
    public int[] dijkstra(int V, int[][] edges, int src) {
        Map<Integer,Map<Integer,Integer>> graph=new HashMap<>();
        for(int e[]:edges){
            int s=e[0],d=e[1],w=e[2];
            graph.putIfAbsent(s,new HashMap<>());
            graph.get(s).put(d,w);
            graph.putIfAbsent(d,new HashMap<>());
            graph.get(d).put(s,w);
        }
       
        int ans[]=new int[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        
        PriorityQueue<int[]>q=new PriorityQueue<>((a,b)->(a[1]-b[1]));
        q.add(new int []{src,0});
        ans[src]=0;
        while(!q.isEmpty()){
            int node[]=q.poll();
            int curr=node[0],dist=node[1];
            
            if(dist>ans[curr])continue;
            Map<Integer,Integer> nei=graph.getOrDefault(curr,new HashMap<>());
            for(int i:nei.keySet()){
                int w=nei.get(i);
                if(dist+w<ans[i]){
                    ans[i]=dist+w;
                    q.add(new int[]{i,ans[i]});
                }
            }
        }
        return ans;
        
    }
}

