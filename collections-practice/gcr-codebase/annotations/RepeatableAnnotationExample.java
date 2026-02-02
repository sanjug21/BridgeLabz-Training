import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(BugReports.class)
@interface BugReport {
	String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
	BugReport[] value();
}

class BugTracker {
	@BugReport(description = "NullPointerException when user input is empty")
	@BugReport(description = "Method crashes when processing large datasets")
	public void processUserInput() {
		System.out.println("Processing user input...");
	}
}

public class RepeatableAnnotationExample {
	public static void main(String[] args) {
		Method[] methods = BugTracker.class.getDeclaredMethods();

		for (Method method : methods) {
			BugReport[] bugReports = method.getAnnotationsByType(BugReport.class);

			if (bugReports.length > 0) {
				System.out.println("Method: " + method.getName());
				for (int i = 0; i < bugReports.length; i++) {
					System.out.println("  " + (i + 1) + ". " + bugReports[i].description());
				}
			}
		}
	}
}
