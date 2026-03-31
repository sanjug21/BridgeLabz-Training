package parcel_tracker;

public class Parcel {
    private String parcelId;

    public Parcel(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelId() {
        return parcelId;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "parcelId='" + parcelId + '\'' +
                '}';
    }
}
