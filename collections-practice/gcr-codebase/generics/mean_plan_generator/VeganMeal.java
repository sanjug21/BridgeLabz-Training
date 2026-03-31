import java.util.Arrays;
import java.util.List;

public class VeganMeal implements MealPlan {
    @Override
    public String getCategory() {
        return "Vegan";
    }

    @Override
    public List<String> getDailyMenu() {
        return Arrays.asList("Green Smoothie", "Quinoa & Black Bean Bowl", "Lentil Soup");
    }
}
