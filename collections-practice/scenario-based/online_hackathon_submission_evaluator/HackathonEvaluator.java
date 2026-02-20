import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HackathonEvaluator {
    private List<Participant> participants;
    private long submissionDeadline;

    public HackathonEvaluator(long submissionDeadline) {
        this.participants = new ArrayList<>();
        this.submissionDeadline = submissionDeadline;
    }

    public void addParticipant(Participant participant) throws LateSubmissionException {
        if (participant.submissionTime > submissionDeadline) {
            throw new LateSubmissionException(
                "Submission rejected for " + participant.name + 
                " - Submitted after deadline"
            );
        }
        participants.add(participant);
        System.out.println("Participant added: " + participant.name);
    }

    public void displayLeaderboard() {
        System.out.println("\n===================================");
        System.out.println("         LEADERBOARD");
        System.out.println("===================================");

        if (participants.isEmpty()) {
            System.out.println("No participants found.");
            return;
        }

        List<Participant> ranked = new ArrayList<>(participants);
        ranked.sort(new Comparator<Participant>() {
            @Override
            public int compare(Participant p1, Participant p2) {
                int scoreCompare = Integer.compare(p2.calculateScore(), p1.calculateScore());
                if (scoreCompare != 0) {
                    return scoreCompare;
                }
                return Long.compare(p1.submissionTime, p2.submissionTime);
            }
        });

        System.out.println("Rank | Participant ID | Name | Score | Passed/Total");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < ranked.size(); i++) {
            Participant p = ranked.get(i);
            int passed = 0;
            for (Boolean result : p.testCaseResults.values()) {
                if (result) passed++;
            }
            System.out.printf("%-4d | %-14s | %-10s | %-5d | %d/%d%n",
                (i + 1), p.participantId, p.name, p.calculateScore(),
                passed, p.testCaseResults.size());
        }
        System.out.println("===================================");
    }

    public void displayParticipantDetails(String participantId) {
        for (Participant p : participants) {
            if (p.participantId.equals(participantId)) {
                System.out.println("\n--- Participant Details ---");
                System.out.println("ID: " + p.participantId);
                System.out.println("Name: " + p.name);
                System.out.println("Submission Time: " + p.submissionTime);
                System.out.println("Total Score: " + p.calculateScore());
                System.out.println("\nTest Case Results:");
                for (String questionId : p.testCaseResults.keySet()) {
                    String status = p.testCaseResults.get(questionId) ? "PASSED" : "FAILED";
                    System.out.println("  " + questionId + ": " + status);
                }
                return;
            }
        }
        System.out.println("Participant not found: " + participantId);
    }

    public void displayAllParticipants() {
        System.out.println("\n--- All Participants ---");
        if (participants.isEmpty()) {
            System.out.println("No participants found.");
            return;
        }
        for (Participant p : participants) {
            System.out.println(p);
        }
    }
}
