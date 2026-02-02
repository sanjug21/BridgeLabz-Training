package custom_object_mapper;
import java.lang.reflect.*;
import java.util.*;

public class CustomObjectMapper {
    
    public static void main(String[] args) throws Exception {
        Map<String, Object> userProps = new HashMap<>();
        userProps.put("username", "john_doe");
        userProps.put("email", "john@example.com");
        userProps.put("age", 30);
        userProps.put("active", true);
        
        User user = (User) toObject(User.class, userProps);
        System.out.println(user);
    }
    
    public static Object toObject(Class<?> clazz, Map<String, Object> properties) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        
        for (Field field : clazz.getDeclaredFields()) {
            if (properties.containsKey(field.getName())) {
                field.setAccessible(true);
                field.set(instance, properties.get(field.getName()));
            }
        }
        return instance;
    }
}
