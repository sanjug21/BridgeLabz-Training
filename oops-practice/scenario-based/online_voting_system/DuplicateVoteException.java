package online_voting_system;

// Exception Handling: Custom Exception for duplicate voting attempts
public class DuplicateVoteException extends Exception {
    public DuplicateVoteException(String message) {
        super(message);
    }
}