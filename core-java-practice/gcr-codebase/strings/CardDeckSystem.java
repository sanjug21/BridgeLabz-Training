import java.util.*;

public class CardDeckSystem {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of players: ");
        int players=sc.nextInt();
        System.out.print("Enter number of cards per player: ");
        int cardsPerPlayer=sc.nextInt();

        if (players*cardsPerPlayer>52) {
            System.out.println("Not enough cards in the deck!");
        } else {
            String[] deck=initializeDeck();
            String[] shuffledDeck=shuffleDeck(deck);
            String[][] hands=distributeCards(shuffledDeck, players, cardsPerPlayer);
            printHands(hands);
        }
        sc.close();
    }

    public static String[] initializeDeck() {
        String[] suits={"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks={"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int numOfCards=suits.length*ranks.length;
        String[] deck=new String[numOfCards];

        for (int i=0;i<ranks.length;i++) {
            for (int j=0;j<suits.length;j++) {
                deck[suits.length*i+j]=ranks[i]+" of "+suits[j];
            }
        }
        return deck;
    }

    public static String[] shuffleDeck(String[] deck) {
        int n=deck.length;
        for (int i=0;i<n;i++) {
            int r=i+(int)(Math.random()*(n-i));
            String temp=deck[r];
            deck[r]=deck[i];
            deck[i]=temp;
        }
        return deck;
    }

    public static String[][] distributeCards(String[] deck, int numPlayers, int cardsPerPlayer) {
        String[][] playerHands=new String[numPlayers][cardsPerPlayer];
        int cardIndex=0;
        for (int i=0;i<numPlayers;i++) {
            for (int j=0;j<cardsPerPlayer;j++) {
                playerHands[i][j]=deck[cardIndex++];
            }
        }
        return playerHands;
    }

    public static void printHands(String[][] hands) {
        for (int i=0;i<hands.length;i++) {
            System.out.println("\nPlayer " + (i + 1) + " cards:");
            for (int j=0;j<hands[i].length;j++) {
                System.out.println(" - " + hands[i][j]);
            }
        }
    }
}