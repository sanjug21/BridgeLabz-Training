
class Session {
    private String sessionId;
    private String topic;
    private String date;

    public Session(String sessionId, String topic, String date) {
        this.sessionId = sessionId;
        this.topic = topic;
        this.date = date;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Session{" +
               "sessionId='" + sessionId + '\'' +
               ", topic='" + topic + '\'' +
               ", date='" + date + '\'' +
               '}';
    }
}
