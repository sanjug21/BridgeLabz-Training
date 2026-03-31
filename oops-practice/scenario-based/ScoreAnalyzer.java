import java.util.InputMismatchException;
import java.util.Scanner;

public class ScoreAnalyzer {
    static Scanner sc = new Scanner(System.in);
    int n;
    double scores[];

    ScoreAnalyzer(int n) {
        this.n = n;
        this.scores = new double[n];
    }

    public void inputScores() {
        for (int i = 0; i < n; i++) {
            System.out.println("Enter score for student " + (i + 1) + ": ");
            // handels invalid input 
            try {
                scores[i] = sc.nextDouble();
                if(scores[i]<0)throw new Exception("Score can not be less than 0");
                if(scores[i]>100)throw new Exception("Score can not be greater than 100");
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter a valid number.");
                i--;
                sc.next();

            } 
            catch (Exception e) {
                System.out.println(e.getMessage());
                i--;
            }
        }
        sc.close();
    }

    public void calculateAverage() {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        System.out.println("Average score: " + (sum / n));
    }

    public void calculateHighest() {
        double highest = Double.MIN_VALUE;
        for (double score : scores) {
            if (score > highest) {
                highest = score;
            }
        }
        System.out.println("Highest score: " + highest);
    }

    public void calculateLowest() {
        double lowest = Double.MAX_VALUE;
        for (double score : scores) {
            if (score < lowest) {
                lowest = score;
            }
        }
        System.out.println("Lowest score: " + lowest);
    }

    public static void main(String[] args) {
        System.out.println("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        ScoreAnalyzer analyzer = new ScoreAnalyzer(n);
        analyzer.inputScores();
        analyzer.calculateAverage();
        analyzer.calculateHighest();
        analyzer.calculateLowest();
    
    }
}