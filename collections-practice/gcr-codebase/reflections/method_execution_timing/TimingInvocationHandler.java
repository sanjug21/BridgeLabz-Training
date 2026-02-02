package method_execution_timing;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TimingInvocationHandler implements InvocationHandler {
    private final Object target;

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = method.invoke(target, args);
            long end = System.nanoTime();
            System.out.println("[TIMING] " + method.getName() + " executed in " + formatNanos(end - start));
            return result;
        } catch (InvocationTargetException e) {
            long end = System.nanoTime();
            System.out.println("[TIMING] " + method.getName() + " failed in " + formatNanos(end - start));
            throw e.getTargetException();
        }
    }

    private String formatNanos(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        }
        if (nanos < 1_000_000) {
            return String.format("%.2f µs", nanos / 1_000.0);
        }
        if (nanos < 1_000_000_000) {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
        return String.format("%.2f s", nanos / 1_000_000_000.0);
    }
}
