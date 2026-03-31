import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
	String task();
	String assignedTo();
	String priority() default "MEDIUM";
}

class ProjectManager {
	@Todo(task = "Implement user authentication", assignedTo = "Alice", priority = "HIGH")
	public void implementAuthentication() {
	}

	@Todo(task = "Add unit tests", assignedTo = "Bob")
	public void addTests() {
	}
}

public class TodoAnnotation {
	public static void main(String[] args) {
		Method[] methods = ProjectManager.class.getDeclaredMethods();

		for (Method method : methods) {
			if (method.isAnnotationPresent(Todo.class)) {
				Todo todo = method.getAnnotation(Todo.class);
				System.out.println("Task: " + todo.task());
				System.out.println("Assigned To: " + todo.assignedTo());
				System.out.println("Priority: " + todo.priority());
				System.out.println();
			}
		}
	}
}
