package backup_processing;

public class BackupProcessor {

    public static void processBackup(Object obj) {
        if (obj instanceof Backupable) {
            System.out.println("Backup queued: " + obj);
        } else {
            System.out.println("Skipped (not backupable): " + obj);
        }
    }

    public static void main(String[] args) {
        PolicyRecord policy = new PolicyRecord("POL-2026-045", "Asha Patel", 1299.99);
        String tempNote = "Temporary cache entry";

        System.out.println("Backup Processing");
        System.out.println("=================");

        processBackup(policy);
        processBackup(tempNote);
    }
}
