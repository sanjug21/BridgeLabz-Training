import java.util.*;
public class StudentTestScore {
    int NoOfStudents;
    // 5 subjects: Math, Science, English, Social Studies, Hindi in that order
    double[][] scores;
    // Analysis data: average, highest, highest Subject Index, lowest, lowest Subject Index
    //for each subject for all students
    double scoreAnalysis[][];
    StudentTestScore(int n){
        this.NoOfStudents=n;
        this.scores=new double[n][5];
        this.scoreAnalysis=new double[n][5];
    }

    void analyzeScores(){
        for(int i=0;i<NoOfStudents;i++){
            int highestIndex=-1;
            int lowestIndex=-1;
            double sum=0;
            double highest=Double.MIN_VALUE;
            double lowest=Double.MAX_VALUE;
            for(int j=0;j<5;j++){
                double score=scores[i][j];
                sum+=score;
                if(score>highest){
                    highest=score;
                    highestIndex=j;
                }
                if(score<lowest){
                    lowest=score;
                    lowestIndex=j;
                }
            }

            // Store analysis data for student i index 0: average, 1: highest, 2: highest Subject Index, 3: lowest, 4: lowest Subject Index
            scoreAnalysis[i][0]=sum/5;
            scoreAnalysis[i][1]=highest;
            scoreAnalysis[i][2]=highestIndex;
            scoreAnalysis[i][3]=lowest;
            scoreAnalysis[i][4]=lowestIndex;
        }
    }
    void displayAverageScores(){
        System.out.println("Average Scores for each student:");
        for(int i=0;i<NoOfStudents;i++){
            System.out.println("Student "+(i+1)+": "+scoreAnalysis[i][0]);
        }
    }
    void displayHighestAndLowestScores(){
        System.out.println("Highest and Lowest Scores for each student:");
        for(int i=0;i<NoOfStudents;i++){
            System.out.println("Student scores "+(i+1)+": Highest in "+Subject.values()[(int)scoreAnalysis[i][2]]+" = "+scoreAnalysis[i][1]+", Lowest in "+Subject.values()[(int)scoreAnalysis[i][4]]+"= "+scoreAnalysis[i][3]);
        }
    }
    // Enum for subjects to improve code readability
    enum Subject{
        MATH,
        SCIENCE,
        ENGLISH,
        SOCIAL_STUDIES,
        HINDI
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Student Test Score Analysis");
        System.out.println("Enter number of students: ");
        int n=sc.nextInt();
        StudentTestScore studentTestScore = new StudentTestScore(n);
        for(int i=0;i<n;i++){
            System.out.println("Enter scores for Student "+(i+1));
            for(int j=0;j<5;j++){
                System.out.println("Enter score for "+Subject.values()[j]+": ");
                try{
                    studentTestScore.scores[i][j]=sc.nextDouble();
                    if(studentTestScore.scores[i][j]<0)throw new Exception("Score can not be less than 0");
                    if(studentTestScore.scores[i][j]>100)throw new Exception("Score can not be greater than 100");
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
        }
        studentTestScore.analyzeScores();
        studentTestScore.displayAverageScores();
        studentTestScore.displayHighestAndLowestScores();
        sc.close();
    }
}
