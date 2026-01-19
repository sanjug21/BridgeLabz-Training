import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReadPerformanceAnalysis {

    private static final String TEST_FILE = "test_large_file.txt";

    public static void main(String[] args) {
        // Sizes in MB as requested
        int[] fileSizes = {1, 100, 500};

        System.out.printf("%-15s %-25s %-25s%n", "File Size (MB)", "FileReader (ms)", "InputStreamReader (ms)");
        System.out.println("-------------------------------------------------------------------");

        for (int size : fileSizes) {
            File file = new File(TEST_FILE);
            try {
                // Generate file (Printing status as 500MB takes time)
                System.out.println("Generating " + size + "MB file...");
                generateFile(file, size);

                // Benchmark FileReader
                // FileReader reads using the default character encoding
                long start = System.currentTimeMillis();
                readUsingFileReader(file);
                long end = System.currentTimeMillis();
                String fileReaderTime = (end - start) + " ms";

                // Benchmark InputStreamReader
                // InputStreamReader reads bytes and decodes them into characters using a specified charset
                start = System.currentTimeMillis();
                readUsingInputStreamReader(file);
                end = System.currentTimeMillis();
                String streamReaderTime = (end - start) + " ms";

                System.out.printf("%-15d %-25s %-25s%n", size, fileReaderTime, streamReaderTime);

            } catch (IOException e) {
                System.err.println("Error processing size " + size + "MB: " + e.getMessage());
            } finally {
                // Cleanup
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    private static void generateFile(File file, int sizeInMB) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // A sample line ~60-70 bytes
            String content = "This is a sample line of text to simulate file content for benchmarking.\n";
            long bytesToWrite = sizeInMB * 1024L * 1024L;
            long bytesWritten = 0;
            while (bytesWritten < bytesToWrite) {
                bw.write(content);
                bytesWritten += content.length();
            }
        }
    }

    private static void readUsingFileReader(File file) throws IOException {
        // FileReader assumes default encoding
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.readLine() != null) {
                // Consume line
            }
        }
    }

    private static void readUsingInputStreamReader(File file) throws IOException {
        // InputStreamReader allows specifying encoding (e.g., UTF-8)
        // Wrapping FileInputStream
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            while (br.readLine() != null) {
                // Consume line
            }
        }
    }
}