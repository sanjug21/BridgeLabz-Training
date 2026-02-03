import org.json.JSONObject;
import org.json.JSONArray;

public class StudentJSONObject {
    public static void main(String[] args) {
        JSONArray subjects = new JSONArray();
        subjects.put("Mathematics");
        subjects.put("Physics");
        subjects.put("Chemistry");

        JSONObject student = new JSONObject();
        student.put("name", "Raj Kumar");
        student.put("age", 25);
        student.put("subjects", subjects);

        System.out.println(student.toString(4));
    }
}