
import java.util.*;

public class GameOfThrows {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("How many games of Rock-Paper-Scissors do you want to play? ");
        int numGames=sc.nextInt();

        int userWins=0;
        int computerWins=0;
        int ties=0;

        for (int i=1;i<=numGames;i++) {
            System.out.println("\n--- Game " + i + " ---");
            System.out.print("Enter your choice (rock, paper, or scissors): ");
            String userChoice=sc.next().toLowerCase();

            if (!userChoice.equals("rock")&&!userChoice.equals("paper")&&!userChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please try again.");
                i--; // Redo this game
                continue;
            }

            String computerChoice=getComputerChoice();
            System.out.println("Computer chose: " + computerChoice);

            String winner=findWinner(userChoice, computerChoice);
            System.out.println("Result: " + winner);

            if (winner.equals("You win!")) userWins++;
            else if (winner.equals("Computer wins!")) computerWins++;
            else ties++;
        }

        System.out.println("\n--- Final Stats ---");
        String[][] stats=calculateWinPercentage(userWins, computerWins, numGames);
        displayStats(stats, ties);

        sc.close();
    }

    public static String getComputerChoice() {
        String[] choices={"rock", "paper", "scissors"};
        int index=new Random().nextInt(choices.length);
        return choices[index];
    }

    public static String findWinner(String user, String computer) {
        if (user.equals(computer)) {
            return "It's a tie!";
        }
        switch (user) {
            case "rock":
                return (computer.equals("scissors"))?"You win!":"Computer wins!";
            case "paper":
                return (computer.equals("rock"))?"You win!":"Computer wins!";
            case "scissors":
                return (computer.equals("paper"))?"You win!":"Computer wins!";
            default:
                return "Invalid game state.";
        }
    }

    public static String[][] calculateWinPercentage(int userWins, int computerWins, int totalGames) {
        double userPercentage=(totalGames==0)?0:((double)userWins/totalGames)*100;
        double computerPercentage=(totalGames==0)?0:((double)computerWins/totalGames)*100;

        String[][] results=new String[2][3];
        results[0][0]="User";
        results[0][1]=String.valueOf(userWins);
        results[0][2]=String.format("%.2f", userPercentage);

        results[1][0]="Computer";
        results[1][1]=String.valueOf(computerWins);
        results[1][2]=String.format("%.2f", computerPercentage);

        return results;
    }

    public static void displayStats(String[][] stats, int ties) {
        System.out.println("Player\t\t| Wins | Win %");
        System.out.println("----------------|------|---------");
        System.out.printf("%-15s | %-4s | %s%%%n", stats[0][0], stats[0][1], stats[0][2]);
        System.out.printf("%-15s | %-4s | %s%%%n", stats[1][0], stats[1][1], stats[1][2]);
        System.out.println("\nNumber of ties: " + ties);
    }
}