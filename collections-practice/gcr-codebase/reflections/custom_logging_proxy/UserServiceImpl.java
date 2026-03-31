package custom_logging_proxy;

import java.util.*;


public class UserServiceImpl implements UserService, AuditService {
    private Map<Integer, String> users = new HashMap<>();
    private List<String> auditLog = new ArrayList<>();
    
    public UserServiceImpl() {
        // Initialize with sample users
        users.put(1, "Alice Johnson");
        users.put(2, "Bob Smith");
        users.put(3, "Charlie Brown");
    }
    
    @Override
    public String getUserById(int userId) {
        String user = users.getOrDefault(userId, "User not found");
        auditLog.add("GET: User " + userId);
        
        // Simulate slight delay
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return user;
    }
    
    @Override
    public void createUser(String username) {
        int id = users.size() + 1;
        users.put(id, username);
        auditLog.add("CREATE: User " + id + " - " + username);
        System.out.println("  → User created with ID: " + id);
    }
    
    @Override
    public void updateUser(int userId, String username) {
        if (users.containsKey(userId)) {
            users.put(userId, username);
            auditLog.add("UPDATE: User " + userId + " - " + username);
            System.out.println("  → User " + userId + " updated");
        } else {
            auditLog.add("UPDATE FAILED: User " + userId + " not found");
        }
    }
    
    @Override
    public void deleteUser(int userId) {
        if (users.remove(userId) != null) {
            auditLog.add("DELETE: User " + userId);
            System.out.println("  → User " + userId + " deleted");
        } else {
            auditLog.add("DELETE FAILED: User " + userId + " not found");
        }
    }
    
    @Override
    public String getAuditLog() {
        return String.join("\n", auditLog);
    }
}
