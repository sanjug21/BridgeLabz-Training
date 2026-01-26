import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedFileCopy {

    private static final int CHUNK_SIZE = 4096;

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java BufferedFileCopy <source> <unbufferedDest> <bufferedDest>");
            return;
        }

        File source = new File(args[0]);
        File unbufferedDest = new File(args[1]);
        File bufferedDest = new File(args[2]);

        if (!source.exists()) {
            System.out.println("Source file not found: " + source.getAbsolutePath());
            return;
        }

        try {
            long unbufferedTime = copyUnbuffered(source, unbufferedDest);
            long bufferedTime = copyBuffered(source, bufferedDest);

            System.out.println("Unbuffered copy time (ms): " + (unbufferedTime / 1_000_000.0));
            System.out.println("Buffered copy time   (ms): " + (bufferedTime / 1_000_000.0));
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    private static long copyUnbuffered(File source, File destination) throws IOException {
        prepareDestination(destination);
        byte[] buffer = new byte[CHUNK_SIZE];
        int bytesRead;
        long start = System.nanoTime();

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return System.nanoTime() - start;
    }

    private static long copyBuffered(File source, File destination) throws IOException {
        prepareDestination(destination);
        byte[] buffer = new byte[CHUNK_SIZE];
        int bytesRead;
        long start = System.nanoTime();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source), CHUNK_SIZE);
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destination), CHUNK_SIZE)) {
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
        return System.nanoTime() - start;
    }

    private static void prepareDestination(File destination) throws IOException {
        File parent = destination.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        if (destination.exists()) {
            destination.delete();
        }
    }
}
