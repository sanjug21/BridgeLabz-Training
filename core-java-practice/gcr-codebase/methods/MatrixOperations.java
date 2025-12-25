import java.util.*;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter rows and cols: ");
        int r=sc.nextInt();
        int c=sc.nextInt();
        
        MatrixOperations mo=new MatrixOperations();
        int[][] m1=mo.createMatrix(r,c);
        int[][] m2=mo.createMatrix(r,c);
        
        System.out.println("Matrix 1:");
        mo.displayMatrix(m1);
        System.out.println("Matrix 2:");
        mo.displayMatrix(m2);
        
        System.out.println("Addition:");
        mo.displayMatrix(mo.add(m1,m2));
        System.out.println("Subtraction:");
        mo.displayMatrix(mo.sub(m1,m2));
        
        if(r==c){
            System.out.println("Multiplication:");
            mo.displayMatrix(mo.mul(m1,m2));
        }
        sc.close();
    }

    public int[][] createMatrix(int r,int c){
        int[][] m=new int[r][c];
        for(int i=0;i<r;i++)for(int j=0;j<c;j++)m[i][j]=(int)(Math.random()*10);
        return m;
    }

    public int[][] add(int[][] m1,int[][] m2){
        int[][] res=new int[m1.length][m1[0].length];
        for(int i=0;i<m1.length;i++)for(int j=0;j<m1[0].length;j++)res[i][j]=m1[i][j]+m2[i][j];
        return res;
    }

    public int[][] sub(int[][] m1,int[][] m2){
        int[][] res=new int[m1.length][m1[0].length];
        for(int i=0;i<m1.length;i++)for(int j=0;j<m1[0].length;j++)res[i][j]=m1[i][j]-m2[i][j];
        return res;
    }

    public int[][] mul(int[][] m1,int[][] m2){
        int[][] res=new int[m1.length][m2[0].length];
        for(int i=0;i<m1.length;i++)for(int j=0;j<m2[0].length;j++)for(int k=0;k<m1[0].length;k++)res[i][j]+=m1[i][k]*m2[k][j];
        return res;
    }

    public void displayMatrix(int[][] m){
        for(int[] row:m){for(int val:row)System.out.print(val+"\t");System.out.println();}
    }
}