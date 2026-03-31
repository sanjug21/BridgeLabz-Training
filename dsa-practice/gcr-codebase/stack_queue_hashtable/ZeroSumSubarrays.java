
import java.util.*;

public class ZeroSumSubarrays {

    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        System.out.println("Array: " + Arrays.toString(arr));
        printAllZeroSumSubarrays(arr);
    }

    public static void printAllZeroSumSubarrays(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        
        List<Integer> startIndices = new ArrayList<>();
        startIndices.add(-1);
        map.put(0, startIndices);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                List<Integer> list = map.get(sum);
                for (int index : list) {
                    System.out.println("Subarray found from Index " + (index + 1) + " to " + i);
                }
                list.add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(sum, list);
            }
        }
    }
}