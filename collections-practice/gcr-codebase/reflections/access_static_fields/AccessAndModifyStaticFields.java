package access_static_fields;

import java.lang.reflect.*;

public class AccessAndModifyStaticFields {
    
    public static void main(String[] args) throws Exception {
        Field field = Configuration.class.getDeclaredField("API_KEY");
        field.setAccessible(true);
        
        String originalValue = (String) field.get(null);
        System.out.println("Original API_KEY: " + originalValue);
        
        field.set(null, "NEW_API_KEY_99999");
        System.out.println("Modified API_KEY: " + Configuration.getApiKey());
    }
}
