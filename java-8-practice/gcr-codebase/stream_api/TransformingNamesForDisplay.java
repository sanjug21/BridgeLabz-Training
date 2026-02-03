import java.util.ArrayList;
import java.util.List;

public class TransformingNamesForDisplay {

    public static void main(String[] args) {
        List<String> customerNames = new ArrayList<>();
        customerNames.add("john smith");
        customerNames.add("emma wilson");
        customerNames.add("michael brown");
        customerNames.add("sarah davis");
        customerNames.add("david johnson");
        customerNames.add("lisa anderson");

        System.out.println("Customer Names (Uppercase, Alphabetically):");
        customerNames.stream()
                .map(name -> name.toUpperCase())
                .sorted()
                .forEach(name -> System.out.println(name));
    }
}
