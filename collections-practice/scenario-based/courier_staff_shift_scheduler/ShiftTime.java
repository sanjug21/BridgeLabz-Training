class ShiftTime {
    String shiftName;
    String startTime;
    String endTime;

    public ShiftTime(String shiftName, String startTime, String endTime) {
        this.shiftName = shiftName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return shiftName + " (" + startTime + " - " + endTime + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ShiftTime shiftTime = (ShiftTime) obj;
        return shiftName.equals(shiftTime.shiftName);
    }

    @Override
    public int hashCode() {
        return shiftName.hashCode();
    }
}
