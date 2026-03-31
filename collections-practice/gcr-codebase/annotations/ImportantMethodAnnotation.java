import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
	String level() default "HIGH";
}

class BusinessLogic {
	@ImportantMethod
	public void processPayment() {
		System.out.println("Processing payment...");
	}

	@ImportantMethod(level = "CRITICAL")
	public void authenticateUser() {
		System.out.println("Authenticating user...");
	}
}

public class ImportantMethodAnnotation {
	public static void main(String[] args) {
		Method[] methods = BusinessLogic.class.getDeclaredMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(ImportantMethod.class)) {
				ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
				System.out.println("Method: " + method.getName());
				System.out.println("Level: " + annotation.level());
			}
		}
	}
}
