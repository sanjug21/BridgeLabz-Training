import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MaxKeyFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Input Map Entries
        System.out.print("Enter number of entries: ");
        int n = sc.nextInt();
        
        Map<String, Integer> map = new HashMap<>();
        System.out.println("Enter key (String) and value (Integer):");
        for(int i = 0; i < n; i++) {
            map.put(sc.next(), sc.nextInt());
        }

        // 2. Find Max Key
        String maxKey = findMaxKey(map);
        System.out.println("Key with highest value: " + maxKey);

        sc.close();
    }

    public static String findMaxKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) return null;
        
        String maxKey = null;
        int maxValue = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }
}