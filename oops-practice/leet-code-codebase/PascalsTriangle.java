import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    // 118. Pascal's Triangle
    // https://leetcode.com/problems/pascals-triangle/
    public static void main(String[] args) {
       int numRows=5;

        PascalsTriangle obj=new PascalsTriangle();
        List<List<Integer>> ans = obj.generate(numRows);
        System.out.println("Pascal's Triangle: " + ans);
       
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle=new ArrayList<>();
        if(numRows==0)return triangle;

        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for(int i=1;i<numRows;i++){
            List<Integer> prevRow=triangle.get(i-1);
            List<Integer> currentRow=new ArrayList<>();
            currentRow.add(1);
            for(int j=1;j<i;j++){
                currentRow.add(prevRow.get(j-1)+prevRow.get(j));
            }
            currentRow.add(1);
            triangle.add(currentRow);
        }
        return triangle;
    }
}