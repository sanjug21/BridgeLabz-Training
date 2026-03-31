import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {
    public static void main(String[] args) {
        IsGraphBipartite solution = new IsGraphBipartite();
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(solution.isBipartite(graph));
    }
    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        int color[]=new int[n];
        for(int i=0;i<n;i++){
            if(color[i]==0){
                color[i]=1;
                Queue<Integer> q=new LinkedList<>();
                q.add(i);
                while(!q.isEmpty()){
                    int curr=q.poll();
                    for(int nei:graph[curr]){
                        if(color[nei]==0){
                            color[nei]=-color[curr];
                            q.add(nei);
                        }else if(color[nei]==color[curr]){
                            return false;
                        }
                    }
                }
            }
           
        }
        return true;
    }
}
