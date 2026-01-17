package repository;

import java.util.*;
import model.Contact;

public class AddressBookRepository {
    private Map<String, List<Contact>> addressBook;
    public AddressBookRepository() {
        addressBook = new HashMap<>();
    }
    // getter  methods for addressBook
    public Map<String, List<Contact>> getAddressBook() {
        return addressBook;
    }
    
    // UC 6: Ability to create multiple unique address books
    public void addNewBook(String bookName) {
        addressBook.put(bookName, new ArrayList<>());
    }


    // UC 1: Ability to create a new address book contact
    // UC 2: Add contact to address book
    // UC 5: Ability to create multiple contact
    public void addContact(String bookName,Contact contact) {
        addressBook.get(bookName).add(contact);
    }

    // UC 3: Ability to edit contact from address book
    public void editContact(String bookName,Contact contact,Contact updatedContact){
                contact.setFirstName(updatedContact.getFirstName());
                contact.setLastName(updatedContact.getLastName());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());
                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());

    }

    // UC 4: Ability to delete contact from address book
    public void deleteContact(String bookName,Contact contact){
        addressBook.get(bookName).remove(contact);
    } 

}
