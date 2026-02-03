import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class CSVToJSON {
    
    public static JSONArray csvToJson(String csvFilePath) throws IOException {
        JSONArray jsonArray = new JSONArray();
        BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
        
        String headerLine = reader.readLine();
        String[] headers = headerLine.split(",");
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            JSONObject jsonObject = new JSONObject();
            
            for (int i = 0; i < headers.length && i < values.length; i++) {
                jsonObject.put(headers[i].trim(), values[i].trim());
            }
            jsonArray.put(jsonObject);
        }
        reader.close();
        return jsonArray;
    }
    
    public static void main(String[] args) {
        try {
            String csvPath = "io-programming-practice\\json\\src\\sample_data.csv";
            Files.write(
                Paths.get(csvPath),
                "id,name,age\n1,Rajesh,28\n2,Priya,26".getBytes()
            );
            
            JSONArray result = csvToJson(csvPath);
            System.out.println("CSV to JSON:");
            System.out.println(result.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
