import org.json.JSONObject;
import org.json.JSONArray;

public class ExtractJSONFields {
    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();

        JSONObject user1 = new JSONObject();
        user1.put("name", "Rajesh Kumar");
        user1.put("email", "rajesh@example.com");
        user1.put("phone", "9876543210");
        jsonArray.put(user1);

        JSONObject user2 = new JSONObject();
        user2.put("name", "Priya Singh");
        user2.put("email", "priya@example.com");
        user2.put("phone", "9876543211");
        jsonArray.put(user2);

        JSONObject user3 = new JSONObject();
        user3.put("name", "Amit Patel");
        user3.put("email", "amit@example.com");
        user3.put("phone", "9876543212");
        jsonArray.put(user3);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject user = jsonArray.getJSONObject(i);
            System.out.println("Name: " + user.getString("name") + ", Email: " + user.getString("email"));
        }
    }
}