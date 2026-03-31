import java.util.*;
public class SnakeAndLadder {
    // these are constants provided or assumed
    static final int WINNING_POSITION = 100;
    static final int MAX_DICE_ROLL = 6;
    static final int No_Play = 0;
    static final int Ladder = 1;
    static final int Snake = 2;
    static List<Integer> playerPositionsRecord;
    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Snake and Ladder game!");
        playerPositionsRecord=new ArrayList<>();
        chooseNumberOfPlayers();   
        System.out.println("Game over!");     
        sc.close();
    }

    //UC1: Player starts at position 0
    public static void startPosition(int player) {
        System.out.println("Player " + player + " starts at position 0");     
    }

    //UC2: Roll the dice to get a number between 1 to 6
    public static int rollDice() {
        int diceRoll=(int)(Math.random()*MAX_DICE_ROLL)+1;
        return diceRoll;
    }

    //UC3: The Player then checks for a Option. They are No Play, Ladder or Snake.-
    public static int checkOption() {
        // 0 - No Play, 1 - Ladder, 2 - Snake
        int option=(int)(Math.random()*10)%3;
        return option;
    }

    //UC4: Repeat till the Player reaches the winning position 100.
    public static void playSinglePlayerGame() {
        int playerPosition=0;
        int diceRoll;
        int option;

        while (playerPosition < WINNING_POSITION) {
            diceRoll = rollDice();
            System.out.println("Dice rolled: " + diceRoll);
            option = checkOption();
            System.out.println("Option: " + option);
            playerPositionsRecord.add(playerPosition);
            switch (option) {
                case No_Play:
                    playerPositionsRecord.add(playerPosition);
                    System.out.println("Player stays at position " + playerPosition);
                    break;
                case Ladder:
                    playerPosition += diceRoll;

                    if (checkWinningPositionExceeds(playerPosition)) {
                        playerPosition -= diceRoll;
                        playerPositionsRecord.add(playerPosition);
                        System.out.println("Player exceeds winning position, stays at " + playerPosition);
                    }else {
                        playerPositionsRecord.add(playerPosition);
                        System.out.println("Player climbs ladder to position " + playerPosition);
                    }
                    break;
                case Snake:
                    playerPosition -= diceRoll;                    
                    if (playerPosition < 0) {
                        playerPosition = 0;
                    }
                    playerPositionsRecord.add(playerPosition);
                    System.out.println("Player slides down snake to position " + playerPosition);
                    break;
            }

            if (playerPosition == WINNING_POSITION) {
                System.out.println("Player wins!");
                break;
            }
        }
    }

    //UC5 : Ensure the Player does not exceed the winning position of 100.
    public static boolean checkWinningPositionExceeds(int playerPosition) {
        return playerPosition > WINNING_POSITION;
    }


    // UC6 : Ability to show the Player Position after every die roll
    public static void showPlayerPositions() {
        System.out.println("Want to know player positions during the game (1 for yes, 0 for no)?");
        int choice = sc.nextInt();
        if (choice == 1) {
            for (int i = 0; i < playerPositionsRecord.size(); i++) {
                System.out.println("Move " + i + ": Position " + playerPositionsRecord.get(i));
            }
        }
    }


    // UC7 :Play the game for two players
    public static int playMultiPlayerGame(int playerPosition,int playerNumber) {

        int diceRoll= rollDice();
        System.out.println("Player " + playerNumber + " rolled: " + diceRoll);
        int option= checkOption();
        switch (option) {
            case No_Play:
                System.out.println("Player " + playerNumber + " stays at position " + playerPosition);
                break;
            case Ladder:
                playerPosition += diceRoll;

                //UC5: Ensure the Player does not exceed the winning position of 100.
                if (checkWinningPositionExceeds(playerPosition)) {
                    playerPosition -= diceRoll;
                    System.out.println("Player " + playerNumber + " exceeds winning position, stays at " + playerPosition);
                } else {
                    System.out.println("Player " + playerNumber + " climbs ladder to position " + playerPosition);
                }
                break;
            case Snake:
                playerPosition -= diceRoll;
                if (playerPosition < 0) {
                    playerPosition = 0;
                }
                System.out.println("Player " + playerNumber + " slides down snake to position " + playerPosition);
                break;
        }
        return playerPosition;
    }

    public static void chooseNumberOfPlayers() {
        System.out.println("Enter number of players (1 or 2):");
        int numberOfPlayers = sc.nextInt();

        if (numberOfPlayers == 1) {
            startGameWithOnePlayer();
        } else if (numberOfPlayers == 2) {
            startGameWithTwoPlayers();
        }else {
            System.out.println("Invalid number of players.");
        }
            
     
    }
    public static void startGameWithOnePlayer() {
        startPosition(0);
        playSinglePlayerGame();
        showPlayerPositions();
    }

    public static void startGameWithTwoPlayers() {
        int positionPlayer1 = 0;
        int positionPlayer2 = 0;
        int turn = 1; // 1 for Player 1's turn, 2 for Player 2's turn
        while (positionPlayer1 != WINNING_POSITION && positionPlayer2 != WINNING_POSITION) {
            if (turn == 1) {
                positionPlayer1 = playMultiPlayerGame(positionPlayer1, 1);
                turn = 2;
            } else {
                positionPlayer2 = playMultiPlayerGame(positionPlayer2, 2);
                turn = 1;
            }
        }
        if (positionPlayer1 == WINNING_POSITION) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }

    }
}