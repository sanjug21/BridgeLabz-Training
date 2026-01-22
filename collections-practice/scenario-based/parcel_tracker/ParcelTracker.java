package parcel_tracker;



class ParcelTracker {
    private DeliveryStage head;
    private Parcel parcel;

    public ParcelTracker(Parcel parcel) {
        this.parcel = parcel;
        this.head = null;
    }

    public void addStage(String stageName) {
        DeliveryStage newStage = new DeliveryStage(stageName);
        if (head == null) {
            head = newStage;
            return;
        }

        DeliveryStage current = head;
        while (current.nextStage != null) {
            current = current.nextStage;
        }
        current.nextStage = newStage;
    }

    public void addIntermediateCheckpoint(String stageName, String afterStage) {
        DeliveryStage newStage = new DeliveryStage(stageName);
        DeliveryStage current = head;

        while (current != null) {
            if (current.stageName.equals(afterStage)) {
                newStage.nextStage = current.nextStage;
                current.nextStage = newStage;
                return;
            }
            current = current.nextStage;
        }

        System.out.println("Stage '" + afterStage + "' not found. Could not add checkpoint.");
    }

    public String trackParcel() {
        if (head == null) {
            return "No tracking stages defined.";
        }

        StringBuilder trackingInfo = new StringBuilder("Tracking " + parcel.getParcelId() + ":\n");
        DeliveryStage current = head;
        while (current != null) {
            trackingInfo.append(" -> ").append(current.stageName);
            current = current.nextStage;
        }

        return trackingInfo.toString();
    }

    public void markAsLost(String stageName) {
        DeliveryStage current = head;
        DeliveryStage previous = null;

        while (current != null) {
            if (current.stageName.equals(stageName)) {
                // Parcel is considered lost at this stage. Effectively cut off the chain.
                if (previous != null) {
                    previous.nextStage = null; // Disconnect the chain after the previous stage
                } else {
                    head = null; // If lost at the first stage, the entire tracking is reset
                }
                System.out.println("Parcel " + parcel.getParcelId() + " marked as lost after stage: " + stageName);
                return;
            }
            previous = current;
            current = current.nextStage;
        }

        System.out.println("Stage '" + stageName + "' not found.  Cannot mark parcel as lost.");
    }

    public static void main(String[] args) {
        // Example Usage
        Parcel parcel = new Parcel("PKG123");
        ParcelTracker tracker = new ParcelTracker(parcel);

        // Define the main delivery stages
        tracker.addStage("Packed");
        tracker.addStage("Shipped");
        tracker.addStage("In Transit");
        tracker.addStage("Delivered");

        // Print initial tracking information
        System.out.println(tracker.trackParcel());

        // Add an intermediate checkpoint
        tracker.addIntermediateCheckpoint("Customs Check", "Shipped");
        System.out.println(tracker.trackParcel());

        // Mark the parcel as lost
        tracker.markAsLost("In Transit");
        System.out.println(tracker.trackParcel()); // Show the truncated tracking

        Parcel parcel2 = new Parcel("PKG456");
        ParcelTracker tracker2 = new ParcelTracker(parcel2);

        tracker2.addStage("Packed");
        tracker2.addStage("Shipped");
        tracker2.addStage("In Transit");
        tracker2.addStage("Delivered");

        tracker2.markAsLost("Packed");
        System.out.println(tracker2.trackParcel()); // prints No tracking stages defined.
    }
}
