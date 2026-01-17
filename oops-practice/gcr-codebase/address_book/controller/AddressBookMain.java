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
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. View Contacts");
            System.out.println("4. Exit");
            int choice=sc.nextInt();

            switch (choice) {
                case 1:
                    String bookName=chooseBookName();
                    addContactUI(bookName);
                    break;
                case 2:
                    bookName=chooseBookName();
                    editContactUI(bookName);
                    break;
                case 3:
                    bookName=chooseBookName();
                    deleteContactUI(bookName);
                    break;

                case 4:
                    viewContactsUI();
                    break;
                case 5:
            
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }

        }

    }
    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book
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
}
