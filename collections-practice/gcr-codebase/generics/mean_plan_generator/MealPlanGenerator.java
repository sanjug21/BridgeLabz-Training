public class MealPlanGenerator {

    // Generic Method to validate and generate the plan.
    public static <T extends MealPlan> Meal<T> generatePlan(String userName, T plan) {
        // Validation Logic
        if (plan == null) {
            throw new IllegalArgumentException("Meal plan cannot be null.");
        }
        if (plan.getDailyMenu().isEmpty()) {
            throw new IllegalArgumentException("Meal plan must contain at least one meal.");
        }

        // Logic to simulate processing
        System.out.println("Validating preferences for " + userName + "...");
        System.out.println("Generating " + plan.getCategory() + " plan...");

        // Return the generic wrapper
        return new Meal<>(userName, plan);
    }
}
