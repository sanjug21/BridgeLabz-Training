
import java.util.*;

public class ScorecardGenerator {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numStudents=sc.nextInt();

        int[][] scores=generateScores(numStudents);
        double[][] stats=calculateStats(scores);
        String[][] grades=calculateGrades(stats);

        displayScorecard(scores,stats,grades);

        sc.close();
    }

    public static int[][] generateScores(int numStudents) {
        Random random=new Random();
        int[][] scores=new int[numStudents][3]; 
        for (int i=0;i<numStudents;i++) {
            // Generate random scores from 35 to 100 
            scores[i][0]=35+random.nextInt(66); // Physics
            scores[i][1]=35+random.nextInt(66); // Chemistry
            scores[i][2]=35+random.nextInt(66); // Math
        }
        return scores;
    }

    public static double[][] calculateStats(int[][] scores) {
        double[][] studentStats=new double[scores.length][3]; // Total, Average, Percentage
        for (int i=0;i<scores.length;i++) {
            int total=scores[i][0]+scores[i][1]+scores[i][2];
            double average=total/3.0;
            double percentage=(total/300.0)*100.0;

            studentStats[i][0]=total;
            // Round to 2 digits
            studentStats[i][1]=Math.round(average*100.0)/100.0;
            studentStats[i][2]=Math.round(percentage*100.0)/100.0;
        }
        return studentStats;
    }

    public static String[][] calculateGrades(double[][] stats) {
        String[][] studentGrades=new String[stats.length][2]; // Percentage, Grade
        for (int i=0;i<stats.length;i++) {
            double percentage=stats[i][2];
            studentGrades[i][0]=String.valueOf(percentage);
            if (percentage>=80) studentGrades[i][1]="A";
            else if (percentage>=70) studentGrades[i][1]="B";
            else if (percentage>=60) studentGrades[i][1]="C";
            else if (percentage>=50) studentGrades[i][1]="D";
            else if (percentage>=40) studentGrades[i][1]="E";
            else studentGrades[i][1]="R";
        }
        return studentGrades;
    }

    public static void displayScorecard(int[][] scores, double[][] stats, String[][] grades) {
        System.out.println("\n--- Student Scorecard ---");
       System.out.println( "Student   | Physics | Chem.  | Math   | Total  | Average   | Percentage | Grade ");
        for (int i=0;i<scores.length;i++) {
            System.out.printf("%-10s | %-7d | %-9d | %-5d | %-7.0f | %-10.2f | %-10.2f | %-5s%n",
                    "Student " + (i + 1),
                    scores[i][0],
                    scores[i][1],
                    scores[i][2],
                    stats[i][0],
                    stats[i][1],
                    stats[i][2],
                    grades[i][1]);
        }
    }
}