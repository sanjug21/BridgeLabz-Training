package online_voting_system;

// OOP: Candidate class
public class Candidate {
    private String candidateId;
    private String name;
    private int voteCount;

    public Candidate(String candidateId, String name) {
        this.candidateId = candidateId;
        this.name = name;
        this.voteCount = 0;
    }

    public String getCandidateId() { return candidateId; }
    public String getName() { return name; }
    public int getVoteCount() { return voteCount; }
    public void incrementVote() { this.voteCount++; }

    @Override
    public String toString() { return name + " (ID: " + candidateId + ")"; }
}