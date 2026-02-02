package custom_logging_proxy;

public interface UserService {
    String getUserById(int userId);
    void createUser(String username);
    void updateUser(int userId, String username);
    void deleteUser(int userId);
}
