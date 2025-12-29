import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ContainsDuplicate {
    // 217. Contains Duplicate
    // https://leetcode.com/problems/contains-duplicate/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of array:");
        int n=sc.nextInt();
        int[] nums=new int[n];
        System.out.println("Enter array elements:");
        for(int i=0;i<n;i++)nums[i]=sc.nextInt();
        
        ContainsDuplicate obj=new ContainsDuplicate();
        boolean ans = obj.containsDuplicate(nums);
        System.out.println("Contains Duplicate: " + ans);
        sc.close();
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            if(set.contains(num))return true;
            set.add(num);
        }
        return false;
    }
}