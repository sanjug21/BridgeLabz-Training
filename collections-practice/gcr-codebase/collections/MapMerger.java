import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapMerger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. Input Map 1
        System.out.print("Enter number of entries for Map 1: ");
        int n1 = sc.nextInt();
        Map<String, Integer> map1 = new HashMap<>();
        System.out.println("Enter key (String) and value (Integer) for Map 1:");
        for(int i = 0; i < n1; i++) {
            map1.put(sc.next(), sc.nextInt());
        }

        // 2. Input Map 2
        System.out.print("Enter number of entries for Map 2: ");
        int n2 = sc.nextInt();
        Map<String, Integer> map2 = new HashMap<>();
        System.out.println("Enter key (String) and value (Integer) for Map 2:");
        for(int i = 0; i < n2; i++) {
            map2.put(sc.next(), sc.nextInt());
        }

        // 3. Merge Maps
        Map<String, Integer> merged = mergeMaps(map1, map2);
        System.out.println("Merged Map: " + merged);

        sc.close();
    }

    public static Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>(map1);
        
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            String key = entry.getKey();
            Integer val = entry.getValue();
            // If key exists, sum the values; otherwise put new entry
            result.put(key, result.getOrDefault(key, 0) + val);
        }
        return result;
    }
}