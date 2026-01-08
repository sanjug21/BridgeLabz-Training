import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class IntersectionOfTwoArrays {
    // 350. Intersection of Two Arrays II
    // https://leetcode.com/problems/intersection-of-two-arrays-ii/
    public static void main(String[] args) {
        int nums1[]={1,2,2,1};
        int nums2[]={2,2};
        IntersectionOfTwoArrays obj=new IntersectionOfTwoArrays();
        int[] ans = obj.intersect(nums1, nums2);
        System.out.println("Intersection: " + Arrays.toString(ans));
       
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int num:nums1){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        List<Integer> list=new ArrayList<>();
        for(int num:nums2){
            if(map.containsKey(num) && map.get(num)>0){
                list.add(num);
                map.put(num,map.get(num)-1);
            }
        }
        int[] res=new int[list.size()];
        for(int i=0;i<list.size();i++)res[i]=list.get(i);
        return res;
    }
}