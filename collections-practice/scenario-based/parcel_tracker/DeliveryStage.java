package parcel_tracker;

public class DeliveryStage {
    String stageName;
    DeliveryStage nextStage;

    public DeliveryStage(String stageName) {
        this.stageName = stageName;
        this.nextStage = null;
    }

    public DeliveryStage(String stageName, DeliveryStage nextStage) {
        this.stageName = stageName;
        this.nextStage = nextStage;
    }

    @Override
    public String toString() {
        return "DeliveryStage{" +
                "stageName='" + stageName + '\'' +
                ", nextStage=" + (nextStage != null ? nextStage.stageName : null) +
                '}';
    }
}