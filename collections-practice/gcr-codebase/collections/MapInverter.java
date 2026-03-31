import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapInverter {

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

        System.out.println("Original Map: " + map);

        // 2. Invert Map
        Map<Integer, List<String>> inverted = invertMap(map);
        System.out.println("Inverted Map: " + inverted);

        sc.close();
    }

    public static Map<Integer, List<String>> invertMap(Map<String, Integer> map) {
        Map<Integer, List<String>> result = new HashMap<>();
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer val = entry.getValue();
            String key = entry.getKey();
            
            result.putIfAbsent(val, new ArrayList<>());
            result.get(val).add(key);
        }
        return result;
    }
}