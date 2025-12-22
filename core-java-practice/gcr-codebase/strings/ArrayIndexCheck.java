

import java.util.*;

public class ArrayIndexCheck {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of names:");
        int size=sc.nextInt();
        String[] names=new String[size];
        
        System.out.println("Enter " + size + " names:");
        for(int i=0;i<size;i++) {
            names[i]=sc.next();
        }

        // Call method to generate exception
        generateException(names);

        // Call method to handle exception
        handleException(names);
        sc.close();
    }

    public static void generateException(String[] arr) {
        System.out.println("Generating ArrayIndexOutOfBoundsException...");
        String val=arr[arr.length]; 
        System.out.println("Value: " + val);
    }

    public static void handleException(String[] arr) {
        System.out.println("Handling ArrayIndexOutOfBoundsException...");
        try {
            String val=arr[arr.length];
            System.out.println("Value: " + val);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException: " + e);
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException: " + e);
        }
    }
}