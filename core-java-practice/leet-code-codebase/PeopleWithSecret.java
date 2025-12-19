import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PeopleWithSecret {

    // 2092. Find All People With Secret
    // https://leetcode.com/problems/find-all-people-with-secret/
    public static void main(String[] args) {
        int n=6;
        int meetings[][]={{1,2,5},{2,3,8},{1,5,10}};
        int firstPerson=1;
        List<Integer> res=findAllPeople(n,meetings,firstPerson);
        System.out.println(res);
        
    }
    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean secret[]=new boolean[n];
        secret[0]=true;
        secret[firstPerson]=true;
        Arrays.sort(meetings,(a,b)->(a[2]-b[2]));
        List<Integer> ans=new ArrayList<>();

        int i=0;
        while(i<meetings.length){
            int time=meetings[i][2];

            List<int[]> sameTime=new ArrayList<>();
            while(i<meetings.length && time==meetings[i][2]){
                sameTime.add(meetings[i]);
                i++;
            }

            Set<Integer> participants=new HashSet<>();
            Map<Integer,List<Integer>> g=new HashMap<>();
            for(int m[]:sameTime){
                g.putIfAbsent(m[0],new ArrayList<>());
                g.putIfAbsent(m[1],new ArrayList<>());
                g.get(m[0]).add(m[1]);
                g.get(m[1]).add(m[0]);
                participants.add(m[0]);
                participants.add(m[1]);                
            }

            Set<Integer> visited=new HashSet<>();
            Queue<Integer> q=new LinkedList<>();
            for(int p:participants){
                if(secret[p]){
                    q.add(p);
                    visited.add(p);
                }
            }

            while(!q.isEmpty()){
                int curr=q.poll();
                for(int nei:g.getOrDefault(curr,new ArrayList<>())){
                    if(!visited.contains(nei)){
                        visited.add(nei);
                        q.add(nei);
                    }
                }
            }
            for(int v:visited)secret[v]=true;
        }

        for(int j=0;j<n;j++){
            if(secret[j])ans.add(j);
        }
        return ans;
    }
}
