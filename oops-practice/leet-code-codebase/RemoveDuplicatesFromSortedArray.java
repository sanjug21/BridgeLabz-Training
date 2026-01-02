public class RemoveDuplicatesFromSortedArray {
    // 26. Remove Duplicates from Sorted Array
    // https://leetcode.com/problems/remove-duplicates-from-sorted-array/
    public static void main(String[] args) {
        

        RemoveDuplicatesFromSortedArray obj=new RemoveDuplicatesFromSortedArray();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = obj.removeDuplicates(nums);
        System.out.println("Count after removal: " + k);

    }

    public int removeDuplicates(int[] nums) {
        if(nums.length==0)return 0;
        int i=0;
        for(int j=1;j<nums.length;j++){
            if(nums[j]!=nums[i]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i + 1;
    }
}