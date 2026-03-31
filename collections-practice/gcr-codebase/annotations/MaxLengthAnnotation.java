import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
	int value();
}

class Users {
	@MaxLength(15)
	private String username;

	public Users(String username) throws IllegalArgumentException {
		Field field = null;
		try {
			field = Users.class.getDeclaredField("username");
			if (field.isAnnotationPresent(MaxLength.class)) {
				MaxLength annotation = field.getAnnotation(MaxLength.class);
				if (username.length() > annotation.value()) {
					throw new IllegalArgumentException("Username exceeds maximum length of " + annotation.value());
				}
			}
		} catch (NoSuchFieldException e) {
			throw new IllegalArgumentException("Field validation error");
		}
		this.username = username;
	}

	public void displayInfo() {
		System.out.println("Username: " + username);
	}
}

public class MaxLengthAnnotation {
	public static void main(String[] args) {
		try {
			Users validUser = new Users("john_doe");
			System.out.println("Valid user created:");
			validUser.displayInfo();
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try {
			Users invalidUser = new Users("this_is_a_very_long_username");
			invalidUser.displayInfo();
		} catch (IllegalArgumentException e) {
			System.out.println("\nValidation Failed: " + e.getMessage());
		}
	}
}
