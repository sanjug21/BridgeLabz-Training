package download_manager;
public class DownloadManager {

    public static void main(String[] args) {
        System.out.println("=== Approach 1: Using Thread Class ===");
        FileDownloaderThread t1 = new FileDownloaderThread("Document.pdf");
        FileDownloaderThread t2 = new FileDownloaderThread("Image.jpg");
        FileDownloaderThread t3 = new FileDownloaderThread("Video.mp4");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Approach 2: Using Runnable Interface ===");
        Thread r1 = new Thread(new FileDownloaderRunnable("Document.pdf"));
        Thread r2 = new Thread(new FileDownloaderRunnable("Image.jpg"));
        Thread r3 = new Thread(new FileDownloaderRunnable("Video.mp4"));

        r1.start();
        r2.start();
        r3.start();

        try {
            r1.join();
            r2.join();
            r3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll downloads complete!");
    }
}
