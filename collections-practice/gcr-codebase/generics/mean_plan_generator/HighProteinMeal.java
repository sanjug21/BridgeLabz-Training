import java.util.Arrays;
import java.util.List;

public class HighProteinMeal implements MealPlan {
    @Override
    public String getCategory() {
        return "High-Protein";
    }

    @Override
    public List<String> getDailyMenu() {
        return Arrays.asList("Greek Yogurt Parfait", "Turkey Wrap", "Baked Salmon with Broccoli");
    }
}
