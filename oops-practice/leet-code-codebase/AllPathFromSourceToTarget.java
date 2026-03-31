import java.util.ArrayList;
import java.util.List;

public class AllPathFromSourceToTarget {
    public static void main(String[] args) {
        AllPathFromSourceToTarget obj=new AllPathFromSourceToTarget();
        int[][] graph={{1,2},{3},{3},{}};
        System.out.println(obj.allPathsSourceTarget(graph));
    }

    List<List<Integer>> ans;
    int n;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans= new ArrayList<>();
        n=graph.length;
        dfs(0,new ArrayList<>(),graph);
        return ans;
    }
    public void dfs(int curr,List<Integer> tmp,int[][] graph){
        tmp.add(curr);
        
        if(curr==n-1){
            ans.add(new ArrayList<>(tmp));
        }
        else{
            for(int i:graph[curr]){
                dfs(i,tmp,graph);
            }
        }
        tmp.remove(tmp.size() - 1);

    }


}
