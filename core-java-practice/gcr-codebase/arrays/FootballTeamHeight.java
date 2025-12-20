import java.util.Scanner;

public class FootballTeamHeight {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double[] heights=new double[11];
        double sum=0;

        // Enter heights for 11 players
        for(int i=0;i<11;i++){
            System.out.print("Enter height for player "+(i+1)+": ");
            heights[i]=sc.nextDouble();
            sum+=heights[i];
        }

        double mean=sum/11;
        System.out.println("Mean height of the football team: "+mean);
        sc.close();
    }
}