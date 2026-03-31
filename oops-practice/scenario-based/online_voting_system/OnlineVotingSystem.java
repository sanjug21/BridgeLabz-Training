package online_voting_system;

// Main Controller
public class OnlineVotingSystem {
    public static void main(String[] args) {
        System.out.println("=== Online Voting System Demo ===");
        
        ElectionService election = new GeneralElectionService();

        // 1. Candidate Management
        election.addCandidate(new Candidate("C1", "Sanju"));
        election.addCandidate(new Candidate("C2", "Shubham"));
        System.out.println();

        // 2. Voter Registration
        Voter v1 = new Voter("V101", "Manish");
        Voter v2 = new Voter("V102", "Sagar");
        Voter v3 = new Voter("V103", "Sarvan");

        election.registerVoter(v1);
        election.registerVoter(v2);
        election.registerVoter(v3);

        System.out.println("\n--- Voting Started ---");

        // 3. Vote Casting
        try {
            election.castVote("V101", "C1");
            election.castVote("V102", "C1");
            
            // Attempt duplicate vote (Exception Handling)
            System.out.println("Attempting duplicate vote for Shubham...");
            election.castVote("V101", "C2"); 
        } catch (DuplicateVoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        try {
            election.castVote("V103", "C2");
        } catch (DuplicateVoteException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // 4. Result Declaration
        election.declareResults();
    }
}