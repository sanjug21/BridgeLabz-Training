
import java.util.*;

public class CircleInUndirectedGraph {

    public static ArrayList<ArrayList<Integer>> g;
    public static boolean visited[];
    
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int edges=sc.nextInt();
        int[][] graph=new int[edges][2];

        for(int i=0;i<edges;i++){
            graph[i][0]=sc.nextInt();
            graph[i][1]=sc.nextInt();
        }

        visited=new boolean[n];
        g=new ArrayList<>();

        for(int i=0;i<n;i++){
            g.add(new ArrayList<>());
        }
        for(int i=0;i<edges;i++){
            g.get(graph[i][0]).add(graph[i][1]);
            g.get(graph[i][1]).add(graph[i][0]);
        }
        // cycle detection using dfs
        boolean isCycle=false;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                if(dfs(i,-1)){
                    isCycle=true;
                    break;
                }
            }
        }
        System.out.println("Is there a cycle: "+isCycle);
        sc.close();
    }


    public static boolean dfs(int curr,int parent){
        visited[curr]=true;
        for(int i=0;i<g.get(curr).size();i++){
            int nei=g.get(curr).get(i);
            if(!visited[nei]){
                if(dfs(nei,curr))return true;
            }
            else if(nei!=parent){
                return true;
            }
        }
        return false;

        
    }

}
