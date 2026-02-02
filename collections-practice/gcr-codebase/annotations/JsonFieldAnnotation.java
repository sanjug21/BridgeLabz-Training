import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
	String name();
}

class User {
	@JsonField(name = "user_id")
	private int userId;

	@JsonField(name = "user_name")
	private String userName;

	@JsonField(name = "email_address")
	private String email;

	public User(int userId, String userName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
	}
}

public class JsonFieldAnnotation {
	public static String toJson(Object obj) {
		StringBuilder json = new StringBuilder("{\n");
		Field[] fields = obj.getClass().getDeclaredFields();

		boolean first = true;
		for (Field field : fields) {
			if (field.isAnnotationPresent(JsonField.class)) {
				field.setAccessible(true);
				JsonField annotation = field.getAnnotation(JsonField.class);
				String jsonKey = annotation.name();

				try {
					Object value = field.get(obj);
					
					if (!first) json.append(",\n");
					first = false;

					json.append("  \"").append(jsonKey).append("\": ");

					if (value instanceof String) {
						json.append("\"").append(value).append("\"");
					} else {
						json.append(value);
					}
				} catch (IllegalAccessException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		}

		json.append("\n}");
		return json.toString();
	}

	public static void main(String[] args) {
		User user = new User(101, "john_doe", "john@example.com");
		String userJson = toJson(user);
		System.out.println(userJson);
	}
}
