package password_strength_validator;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator {

    public static void main(String[] args) {
        List<String> passwords = Arrays.asList(
                "Welcome1!",
                "weakpass",
                "Short1!",
                "NoSpecial123",
                "N0Lower!",
                "Strong#2026"
        );

        System.out.println("Password Strength Validator");
        System.out.println("============================");

        for (String password : passwords) {
            boolean isStrong = SecurityUtils.isStrongPassword(password);
            System.out.println(password + " -> " + (isStrong ? "STRONG" : "WEAK"));
        }
    }
}
