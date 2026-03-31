import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MColoringProblem {

    // https://www.geeksforgeeks.org/problems/m-coloring-problem-1587115620/1
    public static void main(String[] args) {
        MColoringProblem obj=new MColoringProblem();
        int v=4;
        int[][] edges={{0,1},{1,2},{2,3},{3,0}};
        int m=3;
        System.out.println(obj.graphColoring(v, edges, m));
    }
    Map<Integer,List<Integer>> map;
    int color[];
    boolean graphColoring(int v, int[][] edges, int m) {
        map=new HashMap<>();
        for(int i=0;i<v;i++){
            map.put(i,new ArrayList<>());
        }
        for(int i[]:edges){
            int a=i[0],b=i[1];
            map.get(a).add(b);
            map.get(b).add(a);
        }
        color=new int[v];
        return dfs(0,v,m);
        
    }
    public boolean dfs(int currNode,int totalNodes,int m){
        if(currNode==totalNodes)return true;
        for(int c=1;c<=m;c++){
            if(isSafe(c,currNode)){
                color[currNode]=c;
                
                if(dfs(currNode+1,totalNodes,m))return true;
                color[currNode]=0;
            }
        }
        return false;
        
    }
    public boolean isSafe(int c,int currNode){
        for(int nei:map.get(currNode)){
            if(color[nei]==c)return false;
        }
        return true;
    }
    
}
