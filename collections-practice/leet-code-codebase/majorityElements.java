import java.util.*;

public class majorityElements {
    // 229. Majority Element II
    // https://leetcode.com/problems/majority-element-ii/
    public static void main(String[] args) {
        int[] nums = { 3, 2, 3 };
        majorityElements obj = new majorityElements();
        System.out.println(obj.majorityElement(nums));

    }

    public ArrayList<Integer> majorityElement(int[] nums) {

        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3 && candidate1 != candidate2) {
            result.add(candidate2);
        }
        return result;
    }

}
