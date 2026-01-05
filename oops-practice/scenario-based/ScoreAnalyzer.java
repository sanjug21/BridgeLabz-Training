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
            if (sc.hasNextDouble()) {
                double tmp= sc.nextDouble();
                // loops till we get a score b/w 0 and 100
                while(tmp<0 || tmp>100){
                    System.out.println("Invalid score. Please enter a score between 0 and 100.");
                    tmp=sc.nextDouble();
                }
                scores[i] = tmp;
            } else {
                System.out.println("Invalid score type. Please enter a score between 0 and 100.");
                i--;
                sc.next();
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