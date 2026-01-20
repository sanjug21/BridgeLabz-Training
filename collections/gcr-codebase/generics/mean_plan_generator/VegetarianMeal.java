import java.util.Arrays;
import java.util.List;

public class VegetarianMeal implements MealPlan {
    @Override
    public String getCategory() {
        return "Vegetarian";
    }

    @Override
    public List<String> getDailyMenu() {
        return Arrays.asList("Oatmeal with Berries", "Caprese Salad", "Vegetable Stir-fry with Tofu");
    }
}
