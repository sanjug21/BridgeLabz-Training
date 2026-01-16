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
            int choice=sc.nextInt();

            switch (choice) {
                case 1:
                    String bookName=chooseBookName();
                    addContactUI(bookName);
                    break;
            
                default:
                    System.out.println("Invalid Choice. Please try again.");
                    break;
            }

        }

    }
    // UC 1: Ability to create a new address book contact
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
        if(addressBookService.addContactToBook(bookName, contact)){
            System.out.println("Contact added successfully.");
        }else{
            System.out.println("Contact Already Exists in "+bookName+" Book.");
        }

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
}
