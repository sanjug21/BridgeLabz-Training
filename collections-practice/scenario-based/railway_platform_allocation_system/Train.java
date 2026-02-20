import java.time.LocalTime;

class Train implements Comparable<Train> {
    String trainNumber;
    String trainName;
    LocalTime arrivalTime;
    String source;
    String destination;
    int platformPreference;

    public Train(String trainNumber, String trainName, LocalTime arrivalTime, 
                 String source, String destination, int platformPreference) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.arrivalTime = arrivalTime;
        this.source = source;
        this.destination = destination;
        this.platformPreference = platformPreference;
    }

    // Comparable implementation - earlier arrival time has higher priority
    @Override
    public int compareTo(Train other) {
        return this.arrivalTime.compareTo(other.arrivalTime);
    }

    @Override
    public String toString() {
        return trainNumber + " (" + trainName + ") | From: " + source + 
               " | Arrival: " + arrivalTime + " | Preferred Platform: " + platformPreference;
    }
}
