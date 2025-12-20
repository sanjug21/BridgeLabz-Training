import java.util.Scanner;

public class MatrixToArray {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows=sc.nextInt();
        System.out.print("Enter number of columns: ");
        int columns=sc.nextInt();
        int[][] matrix=new int[rows][columns];
        
        // Input matrix elements
        System.out.println("Enter matrix elements:");
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                matrix[i][j]=sc.nextInt();
            }
        }
        int[] array=new int[rows*columns];
        int index=0;

        // Copy 2D array to 1D array
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                array[index]=matrix[i][j];
                index++;
            }
        }
        System.out.print("1D Array elements: ");
        for(int i=0;i<index;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
        sc.close();
    }
}