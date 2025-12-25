import java.util.*;

public class StudentScoreCard {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int n=sc.nextInt();
        
        StudentScoreCard ssc=new StudentScoreCard();
        int[][] scores=ssc.generateScores(n);
        double[][] stats=ssc.calculateStats(scores);
        
        ssc.displayScoreCard(scores,stats);
        sc.close();
    }

    public int[][] generateScores(int n){
        int[][] scores=new int[n][3];
        for(int i=0;i<n;i++){
            scores[i][0]=10+(int)(Math.random()*90); // Physics
            scores[i][1]=10+(int)(Math.random()*90); // Chemistry
            scores[i][2]=10+(int)(Math.random()*90); // Math
        }
        return scores;
    }

    public double[][] calculateStats(int[][] scores){
        double[][] stats=new double[scores.length][3];
        for(int i=0;i<scores.length;i++){
            double total=scores[i][0]+scores[i][1]+scores[i][2];
            double avg=total/3.0;
            stats[i][0]=total;
            stats[i][1]=Math.round(avg*100.0)/100.0;
            stats[i][2]=Math.round(avg*100.0)/100.0; // Percentage same as avg for 100 marks
        }
        return stats;
    }

    public void displayScoreCard(int[][] scores,double[][] stats){
        System.out.println("StdID\tPhy\tChem\tMath\tTotal\tAvg\t%");
        for(int i=0;i<scores.length;i++){
            System.out.println((i+1)+"\t"+scores[i][0]+"\t"+scores[i][1]+"\t"+scores[i][2]+"\t"+stats[i][0]+"\t"+stats[i][1]+"\t"+stats[i][2]);
        }
    }
}