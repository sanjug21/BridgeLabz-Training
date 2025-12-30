import java.util.Scanner;

public class RemoveElement {
    // 27. Remove Element
    // https://leetcode.com/problems/remove-element/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of array:");
        int n=sc.nextInt();
        int[] nums=new int[n];
        System.out.println("Enter array elements:");
        for(int i=0;i<n;i++)nums[i]=sc.nextInt();
        System.out.println("Enter value to remove:");
        int val=sc.nextInt();

        RemoveElement obj=new RemoveElement();
        int k = obj.removeElement(nums, val);
        System.out.println("Count after removal: " + k);
        System.out.print("Modified array: ");
        for(int i=0;i<k;i++) System.out.print(nums[i] + " ");
        sc.close();
    }

    public int removeElement(int[] nums, int val) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }
}