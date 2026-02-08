package download_manager;
class FileDownloaderRunnable implements Runnable {
    private String fileName;

    public FileDownloaderRunnable(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            for (int progress = 0; progress <= 100; progress += 25) {
                System.out.println("[" + Thread.currentThread().getName() + "] Downloading " + fileName + ": " + progress + "%");
                Thread.sleep((long) (Math.random() * 500 + 200));
            }
        } catch (InterruptedException e) {
            System.out.println("Download interrupted: " + fileName);
        }
    }
}
