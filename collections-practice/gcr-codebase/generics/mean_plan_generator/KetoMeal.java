import java.util.Arrays;
import java.util.List;

public class KetoMeal implements MealPlan {
    @Override
    public String getCategory() {
        return "Keto";
    }

    @Override
    public List<String> getDailyMenu() {
        return Arrays.asList("Scrambled Eggs & Bacon", "Chicken Caesar Salad (No Croutons)",
                "Grilled Steak with Asparagus");
    }
}
