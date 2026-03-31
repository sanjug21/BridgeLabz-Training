import java.util.HashMap;
import java.util.Map;

class Participant {
    String participantId;
    String name;
    Map<String, Boolean> testCaseResults;
    long submissionTime;

    public Participant(String participantId, String name, long submissionTime) {
        this.participantId = participantId;
        this.name = name;
        this.submissionTime = submissionTime;
        this.testCaseResults = new HashMap<>();
    }

    public void addTestCaseResult(String questionId, boolean passed) {
        testCaseResults.put(questionId, passed);
    }

    public int calculateScore() {
        int score = 0;
        for (Boolean passed : testCaseResults.values()) {
            if (passed) {
                score += 10;
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return participantId + " | " + name + " | Score: " + calculateScore() + 
               " | Tests Passed: " + countPassedTests() + "/" + testCaseResults.size();
    }

    private int countPassedTests() {
        int count = 0;
        for (Boolean passed : testCaseResults.values()) {
            if (passed) count++;
        }
        return count;
    }
}
