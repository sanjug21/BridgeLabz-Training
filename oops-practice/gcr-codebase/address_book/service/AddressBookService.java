package service;

import java.util.*;

import model.Contact;
import repository.AddressBookRepository;

public class AddressBookService {
    AddressBookRepository addressBookRepository;
    public AddressBookService() {
        addressBookRepository = new AddressBookRepository();
        addressBookRepository.addNewBook("Default");
    }
    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book

    public void addContactToBook(String bookName, Contact contact) {
        try {
            if (addressBookRepository.getAddressBook().get(bookName).contains(contact)) {
                throw new Exception("Contact Already Exists");
            }
            addressBookRepository.addContact(bookName, contact);
            System.out.println("Contact Added Successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        
       
    }
    // UC 3: Ability to edit contact from address book
    public void editContact(String bookName,Contact updatedContact){
        try {
            for (Contact contact : addressBookRepository.getAddressBook().get(bookName)) {
                if (contact.equals(updatedContact)) {
                    addressBookRepository.editContact(bookName, contact, updatedContact);
                    System.out.println("Contact Updated Successfully");
                    return;
                }
            }
            throw new Exception("Contact Not Found");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           
        
    }

    // UC 4: Ability to delete contact from address book
    public void deleteContact(String bookName,String firstName){
        try {
            for(Contact contact:addressBookRepository.getAddressBook().get(bookName)){
                if(contact.getFirstName().equalsIgnoreCase(firstName)){
                    addressBookRepository.getAddressBook().get(bookName).remove(contact);
                    System.out.println("Contact Deleted Successfully");
                    return;
                }
            }
            throw new Exception("Contact Not Found");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<String, List<Contact>> getAllContacts(){
        return addressBookRepository.getAddressBook();
    }

    public List<String> getAddressBookNames(){
        return new ArrayList<>(addressBookRepository.getAddressBook().keySet());
    }


}
