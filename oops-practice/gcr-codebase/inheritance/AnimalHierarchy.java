class Animals {
    String name;
    int age;

    Animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    void makeSound() {
        System.out.println("Animal makes a sound");
    }

    void displayInfo() {
        System.out.println(name + " (" + age + " years old)");
    }
}

class Dog extends Animals {
    Dog(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println("Bark! Bark!");
    }
}

class Cat extends Animals {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println("Meow! Meow!");
    }
}

class Bird extends Animals {
    Bird(String name, int age) {
        super(name, age);
    }

    @Override
    void makeSound() {
        System.out.println("Chirp! Chirp!");
    }
}

public class AnimalHierarchy {
    public static void main(String[] args) {
        // hierarchical inheritance
        Animals[] animals = {
            new Dog("Tuffy", 3),
            new Cat("Shery", 2),
            new Bird("Eagly", 1)
        };

        System.out.println("Animal Hierarchy System:");
        for (Animals animal:animals) {
            animal.displayInfo();
            animal.makeSound();
            System.out.println("---------------------");
        }
    }
}