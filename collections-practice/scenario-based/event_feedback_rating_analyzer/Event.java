class Event {
    String eventId;
    String eventName;
    String date;

    public Event(String eventId, String eventName, String date) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.date = date;
    }

    @Override
    public String toString() {
        return eventId + " | " + eventName + " | " + date;
    }
}
