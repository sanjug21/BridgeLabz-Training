package controller;
import java.util.*;

import model.Contact;
import service.AddressBookService;
public class AddressBookMain {
    static Scanner sc=new Scanner(System.in);
    static AddressBookService addressBookService=new AddressBookService();
    public static void main(String[] args) {
        System.out.println("==== Welcome to Address Book System ====");

        boolean exit=false;

        
        while(!exit){
            System.out.println("---- Main Menu ----");
            System.out.println("1. Add New Address Book");
            System.out.println("2. Work with an Address Book (Add/Edit/Delete/display Contacts)");
            System.out.println("3. Search Person by City");
            System.out.println("4. Search Person by State");
            System.out.println("5. View Persons by City");
            System.out.println("6. View Persons by State");
            System.out.println("7. Count Persons by City");
            System.out.println("8. Count Persons by State");
            System.out.println("9. Display operation (based on Name/City/State/zip)");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();

            switch (choice) {
                case 1:
                    addNewAddressBookUI();
                    break;
                case 2:
                    handelAddressBookOperations();
                    break;
                case 3:
                    // UC 8: Ability to get contant in a particular city
                    System.out.println("Enter City: ");
                    String city=sc.next();
                    displayContacts(city,addressBookService.getContactsByCity(city));
                    break;
                case 4:
                    // UC 8: Ability to get contant in a particular state
                    System.out.println("Enter State: ");
                    String state=sc.next();
                    displayContacts(state,addressBookService.getContactsByState(state));
                    break;
                case 5:
                    // UC 9: Ability to view contacts by city
                    displayDictionary("City",addressBookService.viewContactsByCity());
                    break;
                case 6:
                    // UC 9: Ability to view contacts by state
                    displayDictionary("State",addressBookService.viewContactsByState());
                    break;
                case 7:
                    // UC 10: Ability to count contacts by city
                    System.out.println("Enter City: ");
                    String city1=sc.next();
                    System.out.println("Total contacts in "+city1+": "+addressBookService.countContactsByCity(city1));
                    break;
                case 8:
                    // UC 10: Ability to count contacts by state
                    System.out.println("Enter State: ");
                    String state1=sc.next();
                    System.out.println("Total contacts in "+state1+": "+addressBookService.countContactsByState(state1));
                    break;
                case 9:
                    // UC 11: Ability to sort the contacts in each address book by first name
                    handelSortingContactsOperations();
                    break;

                case 10:
                    exit=true;
                    break;            
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }

        }

    }
    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book
    // UC 3: Ability to edit contact from address book
    // UC 4: Ability to delete contact from address book
    // UC 5: Ability to create multiple contact
    // UC 6: Ability to create multiple unique address books
    // UC 7: Ability to ensure unique contacts in a particular address book

    public static void handelAddressBookOperations(){
        boolean exit=false;
        while(!exit){
            System.out.println("---- Address Book Operations ----");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View all Contacts");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();

            switch (choice) {
                case 1:
                    addContactUI(chooseBookName());
                    break;
                case 2:
                    editContactUI(chooseBookName());
                    break;
                case 3:
                    deleteContactUI(chooseBookName());
                    break;
                case 4:
                    viewContactsUI();
                    break;
                case 5:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }

        }
    }

    // UC 11: Ability to sort the contacts in each address book by first name
    public static void handelSortingContactsOperations(){
        boolean exit=false;
        while(!exit){
            System.out.println("---- Sorting Contacts ----");
            System.out.println("1. Sort by First Name");
            System.out.println("2. Sort by City");
            System.out.println("3. Sort by State");
            System.out.println("4. Sort by Zip");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice=sc.nextInt();
            boolean ascendingOrder;
            switch (choice) {
                case 1:
                    ascendingOrder=chooseOrder();
                    addressBookService.sortContactsByFirstName(ascendingOrder);
                    displaySortedContacts("First Name",ascendingOrder);
                    break;
                case 2:
                    ascendingOrder=chooseOrder();
                    addressBookService.sortContactsByCity(ascendingOrder);
                    displaySortedContacts("City",ascendingOrder);
                    break;
                case 3:
                    ascendingOrder=chooseOrder();
                    addressBookService.sortContactsByState(ascendingOrder);
                    displaySortedContacts("State",ascendingOrder);
                    break;
                case 4:
                    ascendingOrder=chooseOrder();
                    addressBookService.sortContactsByZip(ascendingOrder);
                    displaySortedContacts("Zip",ascendingOrder);
                    break;
                case 5:
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }

        }
    

    }
    public static boolean chooseOrder(){
        boolean exit=false;
        boolean ascendingOrder=false;
        while(!exit){
            System.out.println("---- Choose Order ----");
            System.out.println("1. Ascending Order");
            System.out.println("2. Descending Order");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ascendingOrder=true;
                    exit=true;
                    break;
                case 2:
                    ascendingOrder=false;
                    exit=true;
                    break;
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            
            }
                    
        }
        return ascendingOrder;
    }

    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book
    // UC 5: Ability to create multiple contact
    public static void addContactUI(String bookName) {
        System.out.println("---- Add Contact UI ----\n");
        System.out.println("Enter First Name: ");
        String firstName=sc.next();
        System.out.println("Enter Last Name: ");
        String lastName=sc.next();
        System.out.println("Enter Phone Number: ");
        String phoneNumber=sc.next();
        System.out.println("Enter Email: ");
        String email=sc.next();
        System.out.println("Enter Address: ");
        String address=sc.next();
        System.out.println("Enter City: ");
        String city=sc.next();
        System.out.println("Enter State: ");
        String state=sc.next();
        System.out.println("Enter Zip: ");
        String zip=sc.next();
        Contact contact=new Contact(firstName, lastName, phoneNumber, email, address, city, state, zip);
        addressBookService.addContactToBook(bookName, contact);       

    }

    public static String chooseBookName(){
        List<String> bookNames=addressBookService.getAddressBookNames();
        System.out.println("---- Available Books ----");
        for(int i=0;i<bookNames.size();i++){
            System.out.println((i+1)+". "+bookNames.get(i));
        }
        System.out.println("Enter the book No to Pick or 0 to exit the system: ");
        int choice=sc.nextInt();
        while(true){
            if(choice==0){
                System.exit(0);
            }
            else if(choice>0 && choice<=bookNames.size()){
                return bookNames.get(choice-1);
            }else{
                System.out.println("Invalid Choice. Please try again.");
                choice=sc.nextInt();
            }
        }
    }

    // UC 3: Ability to edit contact from address book
    public static void editContactUI(String bookName){
        System.out.println("---- Edit Contact UI ----\n");
        System.out.println("You need to enter atleast Phone Number or Email or Your Complete Name to update the contact.");
        System.out.println("Enter First Name: ");
        String firstName=sc.next();
        System.out.println("Enter Last Name");
        String lastName=sc.next();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = sc.next();
        System.out.println("Enter Email: ");
        String email = sc.next();
        System.out.println("Enter Address: ");
        String address = sc.next();
        System.out.println("Enter City: ");
        String city = sc.next();
        System.out.println("Enter State: ");
        String state = sc.next();
        System.out.println("Enter Zip: ");
        String zip = sc.next();
        Contact updatedContact=new Contact(firstName, lastName, phoneNumber, email, address, city, state, zip);
        addressBookService.editContact(bookName,updatedContact);

    }

    // UC 4: Ability to delete contact from address book
    public static void deleteContactUI(String bookName){
        System.out.println("---- Delete Contact UI ----\n");
        System.out.println("Enter first name to delete the contact: ");
        String firstName=sc.next();
        addressBookService.deleteContact(bookName,firstName);        
    }

    // UC 6: Ability to create multiple unique address books
    public static void addNewAddressBookUI(){
        System.out.println("---- Add New Book UI ----\n");
        System.out.println("Enter Book Name: ");
        String bookName=sc.next();
        addressBookService.addNewBook(bookName);
    }

   

    public static void viewContactsUI(){
        System.out.println("---- View Contacts UI ----\n");
        Map<String, List<Contact>> addressBook=addressBookService.getAllContacts();

        for(String bookName:addressBook.keySet()){
            System.out.println("Book Name: "+bookName);
            int count=1;
            
            for(Contact contact:addressBook.get(bookName)){
                System.out.println(count+".");
                System.out.println(contact);
                count++;
            }
            System.out.println();
        }
    }
    
    private static void displayContacts(String stateOrCity,List<Contact> contacts) {
        if (contacts == null || contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Contacts in "+stateOrCity+" :");
            int count = 1;
            for (Contact contact : contacts) {
                System.out.println(count + ".");
                System.out.println(contact);
                count++;
            }
            System.out.println();
        }
    }

    private static void displayDictionary(String stateOrCity,Map<String, List<Contact>> map) {
        if (map.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        map.forEach((key, list) -> {
            System.out.println("Contacts in " + key + " "+stateOrCity +":" );
            int count = 1;
            for (Contact contact : list) {
                System.out.println(count + ".");
                System.out.println(contact);
                count++;
            }
            System.out.println();
        });
    }

    private static void displaySortedContacts(String basedOn,boolean ascendingOrder) {
        Map<String, List<Contact>> sortedContacts=addressBookService.getAllContacts();
        String order=ascendingOrder?"Ascending Order":"Decending Order";
        boolean noContacts=true;
        for (Map.Entry<String, List<Contact>> entry : sortedContacts.entrySet()) {
            String key = entry.getKey();
            List<Contact> list = entry.getValue();
            if (list.isEmpty()) continue;
            noContacts=false;
            System.out.println("Sorted in "+order+ " based on "+basedOn+" in "+key+":");
            int count = 1;
            for (Contact contact : list) {
                System.out.println(count + ".");
                System.out.println(contact);
                count++;
            } 
        }
        if(noContacts){
            System.out.println("No contacts found.");
        }
        
    }

}
