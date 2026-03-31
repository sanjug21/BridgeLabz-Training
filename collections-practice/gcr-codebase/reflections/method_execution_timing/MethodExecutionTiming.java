package method_execution_timing;

import java.lang.reflect.Method;

public class MethodExecutionTiming {

    public static void main(String[] args) throws Exception {
        MathService service = new MathServiceImpl();
        Method method = MathServiceImpl.class.getDeclaredMethod("fibonacci", int.class);
        
        long start = System.nanoTime();
        long result = (long) method.invoke(service, 30);
        long end = System.nanoTime();
        
        System.out.println("Result: " + result);
        System.out.println("Time: " + (end - start) / 1_000_000.0 + " ms");
    }
}
