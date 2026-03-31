import java.util.*;

class Contacts {
    String name;
    String phoneNumber;

    Contacts(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

}

public class ContactOrganizer {
    static Scanner sc = new Scanner(System.in);
    // list to store contacts and set to store phone numbers to check for duplicates
    List<Contacts> contacts;
    Set<String> phoneNumbers;

    ContactOrganizer() {
        this.contacts = new ArrayList<>();
        this.phoneNumbers = new HashSet<>();
    }

    void addContact(String name, String phoneNumber) {
        // check for duplicates
        if (phoneNumbers.contains(phoneNumber)) {
            System.out.println("Phone number already exists.");
            return;
        }
        phoneNumbers.add(phoneNumber);
        contacts.add(new Contacts(name, phoneNumber));
    }
    void deleteContact(){
        // as name can be duplicate but phone number is unique
        System.out.print("Enter number to delete:");
        String number = sc.next();
        for(Contacts contact:contacts){
            if(contact.phoneNumber.equals(number)){
                contacts.remove(contact);
                phoneNumbers.remove(number);
                System.out.println("Contact deleted successfully.");
                return;
            }
        }
        System.out.println("No contact found with this number.");
    }

    void displayContacts() {
        for (Contacts contact : contacts) {
            System.out.println("Name: " + contact.name + "\nPhone Number: " + contact.phoneNumber + "\n");
        }
    }

    void searchContact() {
        System.out.print("Enter name to search:");
        String key = sc.next();
        for (Contacts contact : contacts) {
            // will display all the contacts with matching key in name
            if (contact.name.toLowerCase().contains(key.toLowerCase())) {
                System.out.println("Name: " + contact.name + ", Phone Number: " + contact.phoneNumber);
            }
        }
        System.out.println("Contact not found.");
    }

    public static void main(String[] args) {

        ContactOrganizer organizer = new ContactOrganizer();
        System.out.println("----------Contact Organizer----------");

        // to perform specific task for each contact
        while (true) {
            System.out.println("Choose an action: ");
            System.out.println("1. Add contacts");
            System.out.println("2. Display all contacts");
            System.out.println("3. Search for a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    organizer.inputContacts();
                    break;
                case 2:
                    organizer.displayContacts();
                    break;
                case 3:
                    organizer.searchContact();
                    break;
                case 4:
                    organizer.deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

        }
    }

    public void inputContacts() {
        System.out.println("Enter number of contacts to store: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for contact " + (i + 1));
            System.out.println("Enter name: ");
            String name = sc.nextLine();

            // validate phone number 
            boolean validPhoneNumber = false;
            while (!validPhoneNumber) {
                try {
                    System.out.print("Enter phone number: ");
                    String phoneNumber = sc.nextLine();

                    if (!phoneNumber.isEmpty() && phoneNumber.matches("\\d{10}")) {
                        addContact(name, phoneNumber);
                        validPhoneNumber = true;
                    } else {
                        throw new Exception("Invalid phone number. Use 10 digits only.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}