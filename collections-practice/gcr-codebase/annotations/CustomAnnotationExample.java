import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TaskInfo {
	String priority();
	String assignedTo();
}

class TaskManager {
	@TaskInfo(priority = "HIGH", assignedTo = "Alice")
	public void implementLogin() {
		System.out.println("Login feature");
	}

	@TaskInfo(priority = "MEDIUM", assignedTo = "Bob")
	public void fixBugs() {
		System.out.println("Fixing bugs");
	}
}

public class CustomAnnotationExample {
	public static void main(String[] args) {
		Method[] methods = TaskManager.class.getDeclaredMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(TaskInfo.class)) {
				TaskInfo taskInfo = method.getAnnotation(TaskInfo.class);
				System.out.println("Method: " + method.getName());
				System.out.println("Priority: " + taskInfo.priority());
				System.out.println("Assigned To: " + taskInfo.assignedTo());
			}
		}
	}
}
