import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
	String value();
}

class UserSession {
	private String role;

	public UserSession(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}

class SecureSystem {
	@RoleAllowed("ADMIN")
	public void deleteAllUsers() {
		System.out.println("All users deleted!");
	}

	@RoleAllowed("USER")
	public void viewProfile() {
		System.out.println("Displaying user profile...");
	}
}

public class RoleAllowedAnnotation {
	public static void executeMethod(Object obj, String methodName, UserSession session) {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);

			if (method.isAnnotationPresent(RoleAllowed.class)) {
				RoleAllowed annotation = method.getAnnotation(RoleAllowed.class);
				String requiredRole = annotation.value();

				if (!session.getRole().equals(requiredRole)) {
					System.out.println("Access Denied! Required role: " + requiredRole);
					return;
				}
			}

			method.invoke(obj);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		SecureSystem system = new SecureSystem();

		System.out.println("Testing with ADMIN:");
		UserSession adminSession = new UserSession("ADMIN");
		executeMethod(system, "deleteAllUsers", adminSession);

		System.out.println("\nTesting with USER:");
		UserSession userSession = new UserSession("USER");
		executeMethod(system, "deleteAllUsers", userSession);
		executeMethod(system, "viewProfile", userSession);
	}
}
