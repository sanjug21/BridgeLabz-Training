package custom_object_mapper;

public class User {
    private String username;
    private String email;
    private int age;
    private boolean active;
    
    public User() {}
    
    @Override
    public String toString() {
        return String.format("User[username=%s, email=%s, age=%d, active=%b]",
                           username, email, age, active);
    }
}
