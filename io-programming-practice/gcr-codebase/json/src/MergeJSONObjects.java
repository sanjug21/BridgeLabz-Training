import org.json.JSONObject;

public class MergeJSONObjects {
    public static void main(String[] args) {
        try {
            System.out.println("========== MERGE TWO JSON OBJECTS ==========\n");
            
            JSONObject obj1 = new JSONObject();
            obj1.put("name", "Aditya");
            obj1.put("age", 26);
            obj1.put("city", "Bangalore");
            
            System.out.println("First JSON Object:");
            System.out.println(obj1.toString(4));
            
            JSONObject obj2 = new JSONObject();
            obj2.put("department", "Engineering");
            obj2.put("salary", 50000);
            obj2.put("company", "TechCorp");
            
            System.out.println("\nSecond JSON Object:");
            System.out.println(obj2.toString(4));
            
            JSONObject mergedObj = new JSONObject(obj1.toString());
            for (String key : obj2.keySet()) {
                mergedObj.put(key, obj2.get(key));
            }
            
            System.out.println("\n========== MERGED JSON OBJECT ==========");
            System.out.println(mergedObj.toString(4));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
