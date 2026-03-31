import java.util.*;

public class HandshakeCalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int numberOfStudents=sc.nextInt();
        
        HandshakeCalculator handshakeCalculator=new HandshakeCalculator();
        // Calculate maximum handshakes using the formula 
        int handshakes=handshakeCalculator.calculateHandshakes(numberOfStudents);
        
        System.out.println("Maximum number of handshakes: "+handshakes);
        sc.close();
    }

    public int calculateHandshakes(int n){
        return (n*(n-1))/2;
    }
}