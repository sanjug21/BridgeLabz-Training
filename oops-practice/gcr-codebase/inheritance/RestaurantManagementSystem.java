interface Worker {
    void performDuties();
}

// using class name homosapien as Person class alredy exist and creating an error while running the program
class Homosapien {
    String name;
    int id;

    public Homosapien(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }
}

class Chef extends Homosapien implements Worker {
    String specialty;

    public Chef(String name, int id, String specialty) {
        super(name, id);
        this.specialty = specialty;
    }

    @Override
    public void performDuties() {
        System.out.println("Role: Chef");
        System.out.println("Duty: Cooking " + specialty + " dishes.");
    }
}

class Waiter extends Homosapien implements Worker {
    int tablesAssigned;

    public Waiter(String name, int id, int tablesAssigned) {
        super(name, id);
        this.tablesAssigned = tablesAssigned;
    }

    @Override
    public void performDuties() {
        System.out.println("Role: Waiter");
        System.out.println("Duty: Serving " + tablesAssigned + " tables.");
    }
}

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        // multiple inheritance using interface

        Chef chef = new Chef("Gordon", 101, "Italian");
        chef.displayInfo();
        chef.performDuties();
        System.out.println();

        Waiter waiter = new Waiter("John", 202, 5);
        waiter.displayInfo();
        waiter.performDuties();
    }
}