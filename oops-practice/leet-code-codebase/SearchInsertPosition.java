

public class SearchInsertPosition {
    // 35. Search Insert Position
    // https://leetcode.com/problems/search-insert-position/
    public static void main(String[] args) {
        int [] nums={1,3,5,6};
        int target=5;

        SearchInsertPosition obj=new SearchInsertPosition();
        int ans = obj.searchInsert(nums, target);
        System.out.println("Insert Position: " + ans);

    }

    public int searchInsert(int[] nums, int target) {
        int low=0,high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target)return mid;
            else if(nums[mid]<target)low=mid+1;
            else high=mid-1;
        }
        return low;
    }
}