import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo {

    public static void main(String[] args) {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();

        try {
            pos.connect(pis);
        } catch (IOException e) {
            System.out.println("Failed to connect pipes: " + e.getMessage());
            return;
        }

        Thread writer = new Thread(new WriterTask(pos));
        Thread reader = new Thread(new ReaderTask(pis));

        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted");
        }
    }

    private static class WriterTask implements Runnable {
        private final PipedOutputStream output;

        WriterTask(PipedOutputStream output) {
            this.output = output;
        }

        @Override
        public void run() {
            try (PipedOutputStream out = output) {
                String[] messages = {
                        "Hello from writer thread\n",
                        "Streaming data through pipes\n",
                        "End of message\n"
                };
                for (String msg : messages) {
                    out.write(msg.getBytes());
                }
            } catch (IOException e) {
                System.out.println("Writer error: " + e.getMessage());
            }
        }
    }

    private static class ReaderTask implements Runnable {
        private final PipedInputStream input;

        ReaderTask(PipedInputStream input) {
            this.input = input;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytesRead;
            try (PipedInputStream in = input) {
                while ((bytesRead = in.read(buffer)) != -1) {
                    String data = new String(buffer, 0, bytesRead);
                    System.out.print("Reader received: " + data);
                }
            } catch (IOException e) {
                System.out.println("Reader error: " + e.getMessage());
            }
        }
    }
}
