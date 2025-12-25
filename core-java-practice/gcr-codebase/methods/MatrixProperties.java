import java.util.*;

public class MatrixProperties {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        MatrixProperties mp=new MatrixProperties();
        
        System.out.println("2x2 Matrix:");
        int[][] m2=mp.createMatrix(2,2);
        mp.displayMatrix(m2);
        System.out.println("Transpose of 2x2 Matrix:");
        mp.displayMatrix(mp.transpose(m2));
        System.out.println("Determinant of 2x2 Matrix: "+mp.det2x2(m2));
        System.out.println("Inverse of 2x2 Matrix:");
        mp.displayDoubleMatrix(mp.inverse2x2(m2));
        
        System.out.println("3x3 Matrix:");
        int[][] m3=mp.createMatrix(3,3);
        mp.displayMatrix(m3);
        System.out.println("Determinant of 3x3 Matrix: "+mp.det3x3(m3));
        sc.close();
    }

    public int[][] createMatrix(int r,int c){
        int[][] m=new int[r][c];
        for(int i=0;i<r;i++)for(int j=0;j<c;j++)m[i][j]=(int)(Math.random()*10);
        return m;
    }

    public int[][] transpose(int[][] m){
        int[][] res=new int[m[0].length][m.length];
        for(int i=0;i<m.length;i++)for(int j=0;j<m[0].length;j++)res[j][i]=m[i][j];
        return res;
    }

    public int det2x2(int[][] m){
        return m[0][0]*m[1][1]-m[0][1]*m[1][0];
    }

    public int det3x3(int[][] m){
        return m[0][0]*(m[1][1]*m[2][2]-m[1][2]*m[2][1]) - m[0][1]*(m[1][0]*m[2][2]-m[1][2]*m[2][0]) + m[0][2]*(m[1][0]*m[2][1]-m[1][1]*m[2][0]);
    }

    public double[][] inverse2x2(int[][] m){
        int det=det2x2(m);
        if(det==0)return null;
        double[][] inv=new double[2][2];
        inv[0][0]=m[1][1]/(double)det;
        inv[0][1]=-m[0][1]/(double)det;
        inv[1][0]=-m[1][0]/(double)det;
        inv[1][1]=m[0][0]/(double)det;
        return inv;
    }

    public void displayMatrix(int[][] m){
        for(int[] row:m){for(int val:row)System.out.print(val+"\t");System.out.println();}
    }

    public void displayDoubleMatrix(double[][] m){
        if(m==null){
            System.out.println("No Inverse");
            return;
        }
        for(double[] row:m){for(double val:row)System.out.printf("%.2f\t",val);System.out.println();}
    }
}