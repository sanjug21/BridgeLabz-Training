import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}

class Calculator {
	private static Map<String, Object> cache = new HashMap<>();

	@CacheResult
	public long fibonacci(int n) {
		String cacheKey = "fibonacci_" + n;

		if (cache.containsKey(cacheKey)) {
			System.out.println("Returning cached result for fibonacci(" + n + ")");
			return (long) cache.get(cacheKey);
		}

		System.out.println("Computing fibonacci(" + n + ")...");
		long result;
		if (n <= 1) {
			result = n;
		} else {
			result = fibonacci(n - 1) + fibonacci(n - 2);
		}

		cache.put(cacheKey, result);
		return result;
	}
}

public class CacheResultAnnotation {
	public static void main(String[] args) {
		Calculator calc = new Calculator();

		System.out.println("First call:");
		long fib1 = calc.fibonacci(10);
		System.out.println("Result: " + fib1);

		System.out.println("\nSecond call:");
		long fib2 = calc.fibonacci(10);
		System.out.println("Result: " + fib2);
	}
}
