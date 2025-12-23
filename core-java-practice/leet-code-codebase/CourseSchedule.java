import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int numCourses = 4;
        int[][] prerequisites = { {1,0}, {2,1}, {3,2}, {1,3} }; 
        boolean result = cs.canFinish(numCourses, prerequisites);
        System.out.println("Can finish all courses: " + result);
    }
     public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        for (int i = 0; i < numCourses; i++)adj.add(new ArrayList<>()); 
        int[] indegree = new int[numCourses];
        for (int[] pre : prerequisites) { 
            int course = pre[0], prereq = pre[1]; 
            adj.get(prereq).add(course); 
            indegree[course]++; 
        }
        Queue<Integer>q=new LinkedList<>();
        for(int i = 0; i < numCourses; i++)if(indegree[i]==0)q.add(i);
        int count=0;
        while(!q.isEmpty()){
            int curr=q.poll();
            count++;
            for(int i:adj.get(curr)){
                indegree[i]--;
                if(indegree[i]==0)q.add(i);
            }
        }
        return count==numCourses;
    }
}
