import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

class PerformanceTest {
	@LogExecutionTime
	public void calculateSum() {
		int sum = 0;
		for (int i = 0; i < 100000; i++) {
			sum += i;
		}
		System.out.println("Sum: " + sum);
	}

	@LogExecutionTime
	public void calculateSquares() {
		for (int i = 0; i < 50000; i++) {
			Math.pow(i, 2);
		}
		System.out.println("Squares calculated");
	}
}

public class LogExecutionTimeAnnotation {
	public static void executeWithTiming(Object obj, String methodName) {
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName);
			
			if (method.isAnnotationPresent(LogExecutionTime.class)) {
				long startTime = System.nanoTime();
				method.invoke(obj);
				long endTime = System.nanoTime();
				
				double milliseconds = (endTime - startTime) / 1_000_000.0;
				System.out.println("Execution Time: " + milliseconds + " ms\n");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		PerformanceTest test = new PerformanceTest();
		executeWithTiming(test, "calculateSum");
		executeWithTiming(test, "calculateSquares");
	}
}
