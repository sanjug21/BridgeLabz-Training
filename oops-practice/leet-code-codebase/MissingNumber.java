import java.util.Scanner;

public class MissingNumber {
    // 268. Missing Number
    // https://leetcode.com/problems/missing-number/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n (size of array):");
        int n=sc.nextInt();
        int[] nums=new int[n];
        System.out.println("Enter " + n + " numbers (from 0 to " + n + "):");
        for(int i=0;i<n;i++)nums[i]=sc.nextInt();

        MissingNumber obj=new MissingNumber();
        int ans = obj.missingNumber(nums);
        System.out.println("Missing Number: " + ans);
        sc.close();
    }

    public int missingNumber(int[] nums) {
        int n=nums.length;
        int expectedSum=n*(n+1)/2;
        int actualSum=0;
        for(int num:nums)actualSum+=num;
        return expectedSum-actualSum;
    }
}