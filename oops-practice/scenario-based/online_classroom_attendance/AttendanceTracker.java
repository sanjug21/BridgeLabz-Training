
import java.util.*;

class AttendanceTracker {
    private Map<String, Set<String>> attendanceMap;
    private Map<String, Session> sessions;

    public AttendanceTracker() {
        this.attendanceMap = new HashMap<>();
        this.sessions = new HashMap<>();
    }

    public void createSession(Session session) {
        sessions.put(session.getSessionId(), session);
        attendanceMap.put(session.getSessionId(), new HashSet<>());
        System.out.println("Session created: " + session);
    }

    public void markAttendance(String sessionId, String studentId) throws DuplicateAttendanceException {
        if (!sessions.containsKey(sessionId)) {
            System.out.println("Session " + sessionId + " does not exist!");
            return;
        }

        Set<String> attendees = attendanceMap.get(sessionId);
        if (attendees.contains(studentId)) {
            throw new DuplicateAttendanceException("Student " + studentId + " is already marked present for session " + sessionId);
        }

        attendees.add(studentId);
        System.out.println("Attendance marked for Student " + studentId + " in session " + sessionId);
    }

    public void removeAttendance(String sessionId, String studentId) {
        if (!sessions.containsKey(sessionId)) {
            System.out.println("Session " + sessionId + " does not exist!");
            return;
        }

        Set<String> attendees = attendanceMap.get(sessionId);
        if (attendees.remove(studentId)) {
            System.out.println("Attendance removed for Student " + studentId + " from session " + sessionId);
        } else {
            System.out.println("Student " + studentId + " was not present in session " + sessionId);
        }
    }

    public void displayAttendance(String sessionId) {
        if (!sessions.containsKey(sessionId)) {
            System.out.println("Session " + sessionId + " does not exist!");
            return;
        }

        Session session = sessions.get(sessionId);
        Set<String> attendees = attendanceMap.get(sessionId);

        System.out.println("\n===== Attendance for " + session.getTopic() + " (" + sessionId + ") =====");
        System.out.println("Date: " + session.getDate());
        System.out.println("Total Students Present: " + attendees.size());

        if (attendees.isEmpty()) {
            System.out.println("No students present!");
        } else {
            System.out.println("Students Present:");
            int i = 1;
            for (String studentId : attendees) {
                System.out.println(i + ". " + studentId);
                i++;
            }
        }
    }

}

