import java.util.*;

public class StudentQuiz {
    
    String [] questions={
        // 10 question on java
        "What is the size of int variable in Java?",
        "Which of the following is not a Java feature?",
        "Which keyword is used to inherit a class in Java?",
        "Which of these is not a valid Java identifier?",
        "What is the default value of a local variable in Java?",
        "Which of the following is a valid declaration of a char variable?",
        "What makes java robust?",
        "Which of these cannot be used for a variable name in Java?",
        "What is the extension of java code files?",
        "Which of the following is not an OOPS concept in Java?"
    };
    String [][]options={
        // options for each question
        {"16 bit","32 bit","64 bit","8 bit"},
        {"Object-oriented","Robust","Portable","All of the above"},
        {"this","super","extends","implements"},
        {"myVar","_myVar","2myVar","$myVar"},
        {"null","0","Depends on the data type","No default value for local variables"},
        {"char ch = 'A';","char ch = 65;","char ch = '\\u0041';","All of the above"},
        {"Use of pointers","Dynamic memory allocation","Automatic garbage collection","None of the above"},
        {"identifier","keyword","variableName","myVariable"},
        {".java",".jav",".jv",".javafile"},
        {"Encapsulation","Polymorphism","Compilation","Inheritance"}
        };
    String[] correctAnswers = { "B", "B", "C", "C", "D", "D", "C", "B", "A", "C" };
    String[] studentAnswers = new String[10];

    int calculateScore() {
        int score=0;
        for (int i = 0; i < 10; i++) {
            if(correctAnswers[i].equalsIgnoreCase(studentAnswers[i])){
                score+=1;
            }
        }
        return score;
    }
    void displayAnswers(){
        for(int i=0;i<10;i++){
            if(correctAnswers[i].equalsIgnoreCase(studentAnswers[i])){
                System.out.println("Correct answer: "+correctAnswers[i]);
            }
            else{
                System.out.println("Wrong answer: "+studentAnswers[i]+" Correct answer was "+correctAnswers[i]);

            }
        }
    }
    enum option{
        A,
        B,
        C,
        D,
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        StudentQuiz quiz = new StudentQuiz();
        System.out.println("Welcome to Student Quiz!");
        System.out.println();

        System.out.println("Choose A, B, C or D option for each question.");

        for(int i=0;i<quiz.questions.length;i++){
            System.out.println(quiz.questions[i]);
            for(int j=0;j<quiz.options[i].length;j++){
                System.out.println("("+option.values()[j]+") "+quiz.options[i][j]);
            }
            System.out.println("Choose option:");
            quiz.studentAnswers[i]= sc.next();            
        }
        int score=quiz.calculateScore();
        System.out.println("You scored: "+score);

        System.out.println("Detail report:");
        quiz.displayAnswers();

        
        sc.close();
    }
}