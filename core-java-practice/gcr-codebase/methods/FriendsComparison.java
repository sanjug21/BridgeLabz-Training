import java.util.*;

public class FriendsComparison {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int[] ages=new int[3];
        double[] heights=new double[3];
        String[] names={"Amar","Akbar","Anthony"};
        
        for(int i=0;i<3;i++){
            System.out.print("Enter age for "+names[i]+": ");
            ages[i]=sc.nextInt();
            System.out.print("Enter height for "+names[i]+": ");
            heights[i]=sc.nextDouble();
        }
        
        FriendsComparison fc=new FriendsComparison();
        int youngestIndex=fc.findYoungest(ages);
        int tallestIndex=fc.findTallest(heights);
        
        System.out.println("Youngest friend is: "+names[youngestIndex]);
        System.out.println("Tallest friend is: "+names[tallestIndex]);
        sc.close();
    }
    // finds the index of the youngest friend
    public int findYoungest(int[] ages){
        int minIndex=0;
        for(int i=1;i<ages.length;i++)if(ages[i]<ages[minIndex])minIndex=i;
        return minIndex;
    }
    // finds the index of the tallest friend
    public int findTallest(double[] heights){
        int maxIndex=0;
        for(int i=1;i<heights.length;i++)if(heights[i]>heights[maxIndex])maxIndex=i;
        return maxIndex;
    }
}