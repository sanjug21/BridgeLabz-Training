public class PersonalizedMealPlanSystem {
    public static void main(String[] args) {
        try {
            // Scenario 1: User wants a Keto plan
            KetoMeal ketoPreference = new KetoMeal();
            Meal<KetoMeal> aliceMeal = MealPlanGenerator.generatePlan("Alice", ketoPreference);
            aliceMeal.printDetails();

            // Scenario 2: User wants a Vegan plan
            VeganMeal veganPreference = new VeganMeal();
            Meal<VeganMeal> bobMeal = MealPlanGenerator.generatePlan("Bob", veganPreference);
            bobMeal.printDetails();

            // Scenario 3: User wants a High-Protein plan
            HighProteinMeal proteinPreference = new HighProteinMeal();
            Meal<HighProteinMeal> charlieMeal = MealPlanGenerator.generatePlan("Charlie", proteinPreference);
            charlieMeal.printDetails();

        } catch (Exception e) {
            System.err.println("Error generating plan: " + e.getMessage());
        }
    }
}
