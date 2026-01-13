package online_voting_system;

// Abstraction: Interface defining election operations
public interface ElectionService {
    void registerVoter(Voter voter);
    void addCandidate(Candidate candidate);
    // Throws exception if voter tries to vote twice
    void castVote(String voterId, String candidateId) throws DuplicateVoteException;
    void declareResults();
}