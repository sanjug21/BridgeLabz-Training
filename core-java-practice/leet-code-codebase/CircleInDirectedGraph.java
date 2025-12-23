import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CircleInDirectedGraph {
    public static void main(String[] args) {
        CircleInDirectedGraph obj=new CircleInDirectedGraph();
        int V=4;
        int edges[][]={{0,1},{1,2},{2,3},{3,1}}; 
        boolean res=obj.isCyclic(V,edges);
        System.out.println("Graph contains cycle: "+res);
    }
    public boolean isCyclic(int V, int[][] edges) {
        List<List<Integer>> g=new ArrayList<>();
        for(int i=0;i<V;i++)g.add(new ArrayList<>());
        
        int ind[]=new int[V];
        
        for(int i[]:edges){
            int s=i[0],d=i[1];
            g.get(s).add(d);
            ind[d]++;
            
        }
        Queue<Integer> q=new LinkedList<>();
        
        for(int i=0;i<V;i++)if(ind[i]==0)q.add(i);
        int count=0;
        while(!q.isEmpty()){
            int curr=q.poll();
            count++;
            for(int i:g.get(curr)){
                ind[i]--;
                if(ind[i]==0)q.add(i);
            }
            
        }
        return count!=V;
    }
}

