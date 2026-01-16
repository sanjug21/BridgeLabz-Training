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

    public void addNewBook(String bookName) {
        addressBook.putIfAbsent(bookName, new ArrayList<>());
    }


    // UC 1: Ability to create a new address book contact
    public void addContact(String bookName,Contact contact) {
        addressBook.get(bookName).add(contact);
    }




}
