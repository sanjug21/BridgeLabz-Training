package restaurant_order_processing;
class Chef extends Thread {
    private String dishName;
    private double cookingTime;

    public Chef(String name, String dishName, double cookingTime) {
        super(name);
        this.dishName = dishName;
        this.cookingTime = cookingTime;
    }

    @Override
    public void run() {
        System.out.println(getName() + " started preparing " + dishName);

        int[] milestones = {25, 50, 75, 100};
        try {
            for (int milestone : milestones) {
                Thread.sleep((long) (cookingTime * 1000 / 4));
                System.out.println(getName() + " preparing " + dishName + ": " + milestone + "% complete");
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted");
        }
    }
}
