package dependency_injection;

import java.lang.reflect.*;

public class DependencyInjectionExample {
    
    public static void main(String[] args) throws Exception {
        EmailService emailService = new EmailService();
        LoggerService logger = new LoggerService();
        
        Class<?> clazz = UserNotificationService.class;
        Constructor<?> constructor = clazz.getDeclaredConstructor(EmailService.class, LoggerService.class);
        
        UserNotificationService service = (UserNotificationService) constructor.newInstance(emailService, logger);
        service.notifyUser("john@example.com", "Welcome!");
    }
}

class EmailService {
    public void sendEmail(String recipient, String subject, String body) {
        System.out.println("Sending email to: " + recipient);
        System.out.println("Subject: " + subject);
    }
}

class LoggerService {
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

class UserNotificationService {
    private EmailService emailService;
    private LoggerService logger;
    
    public UserNotificationService(EmailService emailService, LoggerService logger) {
        this.emailService = emailService;
        this.logger = logger;
    }
    
    public void notifyUser(String email, String message) {
        logger.log("Preparing notification for: " + email);
        emailService.sendEmail(email, "Notification", message);
        logger.log("Notification sent");
    }
}
