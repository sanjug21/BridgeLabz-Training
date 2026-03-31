package generate_json;

import java.lang.reflect.*;

public class GenerateJsonRepresentation {

    public static void main(String[] args) {
        Book book = new Book("Clean Code", "Robert C. Martin", 2008, 33.50);
        System.out.println(toJson(book));
    }

    public static String toJson(Object obj) {
        if (obj == null) return "null";
        
        Class<?> clazz = obj.getClass();
        
        if (obj instanceof String) {
            return "\"" + obj + "\"";
        }
        
        if (obj instanceof Number || obj instanceof Boolean) {
            return String.valueOf(obj);
        }
        
        StringBuilder json = new StringBuilder("{");
        Field[] fields = clazz.getDeclaredFields();
        int count = 0;
        
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) continue;
            
            field.setAccessible(true);
            try {
                if (count > 0) json.append(", ");
                json.append("\"").append(field.getName()).append("\": ")
                    .append(toJson(field.get(obj)));
                count++;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        json.append("}");
        return json.toString();
    }
}
