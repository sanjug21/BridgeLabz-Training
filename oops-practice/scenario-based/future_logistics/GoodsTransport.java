abstract class GoodsTransport {
    protected String transportId;
    protected String tranportDate;
    protected int tranportRating;

    GoodsTransport(String transportId, String tranportDate, int tranportRating) {
        this.transportId = transportId;
        this.tranportDate = tranportDate;
        this.tranportRating = tranportRating;
    }
    public String getTransportId() {
        return transportId;
    }

    public String getTranportDate() {
        return tranportDate;
    }

    public int getTranportRating() {
        return tranportRating;
    }   

    abstract public  String vehicleSelection();
    abstract public double calculateTotalCharge();

}
