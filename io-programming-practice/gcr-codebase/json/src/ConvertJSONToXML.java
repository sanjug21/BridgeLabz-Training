import org.json.JSONObject;
import java.util.Iterator;

public class ConvertJSONToXML {
    
    public static String jsonToXml(JSONObject json, String rootElement) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\"?>\n");
        xml.append("<").append(rootElement).append(">\n");
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            xml.append("  <").append(key).append(">").append(value.toString()).append("</").append(key).append(">\n");
        }
        xml.append("</").append(rootElement).append(">");
        return xml.toString();
    }
    
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("id", 1);
        json.put("name", "Rajesh Kumar");
        json.put("email", "rajesh@example.com");
        
        System.out.println("JSON to XML Conversion:");
        System.out.println(jsonToXml(json, "Employee"));
    }
}
