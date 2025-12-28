import java.util.*;

public class CompGuesser {
    static Scanner sc=new Scanner(System.in);
    static int low=1,high=100;

    public static void main(String[] args) {
        System.out.println("Think of a number between 1 and 100");
        play();
        sc.close();
    }

    static void play(){
        boolean done=false;
        while(!done){
            int g=genGuess();
            char f=getFeed(g);
            if(f=='c') done=true;
            else nextRange(f,g);
        }
        System.out.println("I guessed it!");
    }

    static int genGuess(){
        if(low>high) return low;
        return (int)(Math.random()*(high-low+1)+low);
    }

    static char getFeed(int g){
        System.out.print("Is "+g+" (h)igh, (l)ow, or (c)orrect? ");
        return sc.next().charAt(0);
    }

    static void nextRange(char f,int g){
        if(f=='h') high=g-1;
        else if(f=='l') low=g+1;
    }
}