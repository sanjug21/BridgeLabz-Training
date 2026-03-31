import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlight {
    public static void main(String[] args) {
        CheapestFlight cp=new CheapestFlight();
        int n = 4;
        int [][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src = 0, dst = 3, k = 1;
        int ans=cp.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("Cheapest flight with atmost k stop is:"+ ans);
    }
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
       Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(); 
       for (int[] f : flights) { 
        graph.putIfAbsent(f[0], new HashMap<>()); 
        graph.get(f[0]).put(f[1], f[2]); 
        } 
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); 
        pq.offer(new int[]{0, src, 0});
        
        int[][] dist = new int[n][k+2]; 
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE); 
        dist[src][0] = 0;

        while(!pq.isEmpty()){
            int curr[]=pq.poll();
            int cost=curr[0];
            int node=curr[1];
            int stops=curr[2];

            if(node==dst)return cost;
            if(stops<=k && graph.containsKey(node)){
                for(int key:graph.get(node).keySet()){
                    int w=graph.get(node).get(key);
                    if(cost+w<dist[key][stops+1]){
                        dist[key][stops+1]=cost+w;
                        pq.add(new int[]{cost+w,key,stops+1});
                    }
                }
            }
        }

        return -1;
    }

}
