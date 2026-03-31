import java.util.HashMap;
import java.util.Scanner;

public class MajorityElement {
    // 169. Majority Element
    // https://leetcode.com/problems/majority-element/
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size of array:");
        int n=sc.nextInt();
        int[] nums=new int[n];
        System.out.println("Enter array elements:");
        for(int i=0;i<n;i++)nums[i]=sc.nextInt();

        MajorityElement obj=new MajorityElement();
        int ans = obj.majorityElement(nums);
        System.out.println("Majority Element: " + ans);
        sc.close();
    }

    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> count=new HashMap<>();
        int majorityCount=nums.length/2;
        for(int num:nums){
            count.put(num,count.getOrDefault(num,0)+1);
            if(count.get(num)>majorityCount)return num;
        }
        return -1;
    }
}