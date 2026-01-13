package online_voting_system;

import java.util.HashMap;
import java.util.Map;

// Implementation of Election Service
public class GeneralElectionService implements ElectionService {
    private Map<String, Voter> voters;
    private Map<String, Candidate> candidates;

    public GeneralElectionService() {
        this.voters = new HashMap<>();
        this.candidates = new HashMap<>();
    }

    @Override
    public void registerVoter(Voter voter) {
        voters.put(voter.getVoterId(), voter);
        System.out.println("Voter registered: " + voter.getName());
    }

    @Override
    public void addCandidate(Candidate candidate) {
        candidates.put(candidate.getCandidateId(), candidate);
        System.out.println("Candidate added: " + candidate.getName());
    }

    @Override
    public void castVote(String voterId, String candidateId) throws DuplicateVoteException {
        Voter voter = voters.get(voterId);
        Candidate candidate = candidates.get(candidateId);

        if (voter == null) {
            System.out.println("Error: Voter ID " + voterId + " not found.");
            return;
        }
        if (candidate == null) {
            System.out.println("Error: Candidate ID " + candidateId + " not found.");
            return;
        }

        if (voter.hasVoted()) {
            throw new DuplicateVoteException("Voter " + voter.getName() + " has already voted.");
        }

        candidate.incrementVote();
        voter.setHasVoted(true);
        System.out.println("Vote cast successfully by " + voter.getName() + " for " + candidate.getName());
    }

    @Override
    public void declareResults() {
        System.out.println("\n=== Election Results ===");
        Candidate winner = null;
        int maxVotes = -1;

        for (Candidate c : candidates.values()) {
            System.out.println(c.getName() + ": " + c.getVoteCount() + " votes");
            if (c.getVoteCount() > maxVotes) {
                maxVotes = c.getVoteCount();
                winner = c;
            }
        }
        System.out.println("------------------------");
        if (winner != null && maxVotes > 0) {
            System.out.println("Winner: " + winner.getName() + " with " + maxVotes + " votes.");
        } else {
            System.out.println("No votes cast or tie.");
        }
        System.out.println("========================\n");
    }
}