import org.json.JSONObject;
import org.json.JSONArray;

public class FilterJSONByAge {
    static class Person {
        String name;
        int age;
        String city;

        Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }
    }

    public static void main(String[] args) {
        Person[] people = {
                new Person("Arun", 28, "Delhi"),
                new Person("Bhavna", 22, "Mumbai"),
                new Person("Chirag", 35, "Bangalore"),
                new Person("Deepika", 24, "Pune"),
                new Person("Eshan", 30, "Hyderabad")
        };

        JSONArray peopleArray = new JSONArray();
        for (Person person : people) {
            JSONObject personJSON = new JSONObject();
            personJSON.put("name", person.name);
            personJSON.put("age", person.age);
            personJSON.put("city", person.city);
            peopleArray.put(personJSON);
        }

        JSONArray filtered = new JSONArray();
        for (int i = 0; i < peopleArray.length(); i++) {
            JSONObject person = peopleArray.getJSONObject(i);
            if (person.getInt("age") > 25) {
                filtered.put(person);
            }
        }

        System.out.println(filtered.toString(4));
    }
}