import org.json.JSONObject;
import org.json.JSONArray;

public class GenerateJSONReportFromDB {
    
    public static JSONArray generateReportFromDatabase() {
        JSONArray reportArray = new JSONArray();
        
        String[][] data = {
            {"1", "Rajesh Kumar", "Engineering", "75000"},
            {"2", "Priya Singh", "Management", "65000"},
            {"3", "Amit Patel", "QA", "55000"},
            {"4", "Deepika Sharma", "HR", "60000"}
        };
        
        String[] headers = {"EmployeeID", "Name", "Department", "Salary"};
        
        for (String[] row : data) {
            JSONObject record = new JSONObject();
            for (int i = 0; i < headers.length; i++) {
                record.put(headers[i], row[i]);
            }
            reportArray.put(record);
        }
        
        return reportArray;
    }
    
    public static void main(String[] args) {
        JSONArray records = generateReportFromDatabase();
        System.out.println("JSON Report from Database:");
        System.out.println(records.toString(4));
    }
}
