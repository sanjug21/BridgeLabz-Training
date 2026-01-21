public class mostFrequentEvenElement {
    // 2404. Most Frequent Even Element
    // https://leetcode.com/problems/most-frequent-even-element/
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,4,4,1};
        mostFrequentEvenElement obj = new mostFrequentEvenElement();
        System.out.println(obj.mostFrequentEven(nums));
    }
    public int mostFrequentEven(int[] nums) {
        int[] freq = new int[100001];
        for (int num : nums) {
            if (num % 2 == 0) {
                freq[num]++;
            }
        }
        int maxFreq = 0;
        int result = -1;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                result = i;
            }
        }
        return result;
    }

}
