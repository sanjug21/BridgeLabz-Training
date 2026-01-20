public class Meal<T extends MealPlan> {
    private String userName;
    private T mealPlan;

    public Meal(String userName, T mealPlan) {
        this.userName = userName;
        this.mealPlan = mealPlan;
    }

    public void printDetails() {
        System.out.println("------------------------------------------------");
        System.out.println("User: " + userName);
        System.out.println("Plan Category: " + mealPlan.getCategory());
        System.out.println("Menu: " + mealPlan.getDailyMenu());
        System.out.println("------------------------------------------------");
    }

    public T getMealPlan() {
        return mealPlan;
    }
}
