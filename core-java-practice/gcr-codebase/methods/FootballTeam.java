import java.util.*;

public class FootballTeam {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] heights=new int[11];
        // Take input for 11 players
        for(int i=0;i<11;i++){
            System.out.print("Enter height for player "+(i+1)+": ");
            heights[i]=sc.nextInt();
        }
        FootballTeam team=new FootballTeam();
        int tallest=team.findTallest(heights);
        System.out.println("Tallest height on the team is: "+tallest);
        sc.close();
    }
    public int findTallest(int[] heights){
        int max=heights[0];
        for(int i=1;i<heights.length;i++)if(heights[i]>max)max=heights[i];
        return max;
    }
}