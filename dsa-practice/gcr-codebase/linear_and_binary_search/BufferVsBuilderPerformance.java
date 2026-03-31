public class BufferVsBuilderPerformance {

    public static void main(String[] args) {
        int iterations = 1000000;
        String text = "hello";

        // --- StringBuffer Test ---
        long startTimeBuffer = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(text);
        }
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = endTimeBuffer - startTimeBuffer;

        // --- StringBuilder Test ---
        long startTimeBuilder = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(text);
        }
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = endTimeBuilder - startTimeBuilder;

        // Output results in milliseconds for readability
        System.out.println("Performance Comparison (" + iterations + " concatenations):");
        System.out.println("StringBuffer time: " + (durationBuffer / 1_000_000) + " ms");
        System.out.println("StringBuilder time: " + (durationBuilder / 1_000_000) + " ms");
    }
}