import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class GymMember {
    String name;
    LocalDate expiryDate;

    public GymMember(String name, LocalDate expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}

public class FilteringExpiringMemberships {

    public static void main(String[] args) {
        List<GymMember> members = new ArrayList<>();
        members.add(new GymMember("John", LocalDate.now().plusDays(15)));
        members.add(new GymMember("Sarah", LocalDate.now().plusDays(45)));
        members.add(new GymMember("Mike", LocalDate.now().plusDays(20)));
        members.add(new GymMember("Emma", LocalDate.now().plusDays(60)));
        members.add(new GymMember("David", LocalDate.now().plusDays(5)));
        members.add(new GymMember("Lisa", LocalDate.now().plusDays(100)));
        members.add(new GymMember("Tom", LocalDate.now().plusDays(25)));

        LocalDate thirtyDaysFromNow = LocalDate.now().plusDays(30);

        System.out.println("Members with Expiring Memberships (Next 30 Days):");
        members.stream()
                .filter(m -> m.getExpiryDate().isBefore(thirtyDaysFromNow) || m.getExpiryDate().isEqual(thirtyDaysFromNow))
                .forEach(m -> System.out.println(m.getName() + " - Expires: " + m.getExpiryDate()));
    }
}
