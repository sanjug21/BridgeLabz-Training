import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class ImageByteArrayConverter {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java ImageByteArrayConverter <sourceImage> <destinationImage>");
            return;
        }

        File source = new File(args[0]);
        File destination = new File(args[1]);

        if (!source.exists()) {
            System.out.println("Source image not found: " + source.getAbsolutePath());
            return;
        }

        try {
            byte[] imageBytes = toByteArray(source);
            fromByteArray(imageBytes, destination);
            boolean identical = verifyFiles(source, destination);
            System.out.println("Copy created at: " + destination.getAbsolutePath());
            System.out.println("Files identical: " + identical);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }

    private static byte[] toByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    private static void fromByteArray(byte[] data, File destination) throws IOException {
        File parent = destination.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             FileOutputStream fos = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bais.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    private static boolean verifyFiles(File original, File copy) throws IOException {
        byte[] originalBytes = Files.readAllBytes(original.toPath());
        byte[] copyBytes = Files.readAllBytes(copy.toPath());
        return Arrays.equals(originalBytes, copyBytes);
    }
}
