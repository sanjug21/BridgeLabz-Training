import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReadWrite {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileReadWrite <source> <destination>");
            return;
        }

        File source = new File(args[0]);
        File destination = new File(args[1]);

        if (!source.exists()) {
            System.out.println("Source file does not exist: " + source.getAbsolutePath());
            return;
        }

        File parent = destination.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        byte[] buffer = new byte[4096];
        int bytesRead;

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied to: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
