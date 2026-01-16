import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PerformanceBenchmark {

    // Using the existing file path from your context
    private static final String FILE_PATH = "d:\\Software\\capgemini\\BridgeLabz-Training\\dsa-practice\\gcr-codebase\\linear_and_binary_search\\file.txt";
    private static final int CONCAT_ITERATIONS = 1_000_000;

    public static void main(String[] args) {
        System.out.println("=== PART 1: String Concatenation Benchmark (" + CONCAT_ITERATIONS + " iterations) ===");
        benchmarkStringConcatenation();

        System.out.println("\n=== PART 2: File Reading Benchmark ===");
        // Note: For a true stress test, a larger file (100MB+) is recommended. 
        // We will use the existing file.txt for demonstration.
        benchmarkFileReading();
    }

    private static void benchmarkStringConcatenation() {
        String text = "hello";

        // 1. StringBuffer Benchmark
        long startTimeBuffer = System.nanoTime();
        StringBuffer sbBuffer = new StringBuffer();
        for (int i = 0; i < CONCAT_ITERATIONS; i++) {
            sbBuffer.append(text);
        }
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = (endTimeBuffer - startTimeBuffer) / 1_000_000; // Convert to ms

        // 2. StringBuilder Benchmark
        long startTimeBuilder = System.nanoTime();
        StringBuilder sbBuilder = new StringBuilder();
        for (int i = 0; i < CONCAT_ITERATIONS; i++) {
            sbBuilder.append(text);
        }
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = (endTimeBuilder - startTimeBuilder) / 1_000_000; // Convert to ms

        System.out.println("StringBuffer Time: " + durationBuffer + " ms");
        System.out.println("StringBuilder Time: " + durationBuilder + " ms");
    }

    private static void benchmarkFileReading() {
        // 1. FileReader Benchmark
        long startFileReader = System.nanoTime();
        long wordCountFileReader = countWordsUsingFileReader(FILE_PATH);
        long endFileReader = System.nanoTime();
        long durationFileReader = (endFileReader - startFileReader) / 1_000; // Microseconds for file IO precision

        // 2. InputStreamReader Benchmark
        long startStreamReader = System.nanoTime();
        long wordCountStreamReader = countWordsUsingInputStreamReader(FILE_PATH);
        long endStreamReader = System.nanoTime();
        long durationStreamReader = (endStreamReader - startStreamReader) / 1_000; // Microseconds

        System.out.println("FileReader -> Words: " + wordCountFileReader + ", Time: " + durationFileReader + " us");
        System.out.println("InputStreamReader -> Words: " + wordCountStreamReader + ", Time: " + durationStreamReader + " us");
    }

    private static long countWordsUsingFileReader(String path) {
        long count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count += line.trim().split("\\s+").length;
                }
            }
        } catch (IOException e) {
            System.out.println("FileReader Error: " + e.getMessage());
        }
        return count;
    }

    private static long countWordsUsingInputStreamReader(String path) {
        long count = 0;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    count += line.trim().split("\\s+").length;
                }
            }
        } catch (IOException e) {
            System.out.println("InputStreamReader Error: " + e.getMessage());
        }
        return count;
    }
}