import org.json.JSONObject;

public class JavaObjectToJSON {
    static class Car {
        String brand;
        String model;
        int year;
        double price;
        String color;

        Car(String brand, String model, int year, double price, String color) {
            this.brand = brand;
            this.model = model;
            this.year = year;
            this.price = price;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        Car car = new Car("Toyota", "Fortuner", 2023, 2500000.00, "Black");

        JSONObject carJSON = new JSONObject();
        carJSON.put("brand", car.brand);
        carJSON.put("model", car.model);
        carJSON.put("year", car.year);
        carJSON.put("price", car.price);
        carJSON.put("color", car.color);

        System.out.println(carJSON.toString(4));
    }
}