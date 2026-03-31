package parcel_tracker;
class StageNode {
    String stageName;
    StageNode next;

    public StageNode(String stageName) {
        this.stageName = stageName;
        this.next = null;
    }
}