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

    public boolean addContactToBook(String bookName, Contact contact) {
        if(!addressBookRepository.getAddressBook().containsKey(bookName)){
            return false;
        }
        if(addressBookRepository.getAddressBook().get(bookName).contains(contact)){
            return false;
        }
        addressBookRepository.addContact(bookName, contact);
        return true;
    }

    public List<String> getAddressBookNames(){
        return new ArrayList<>(addressBookRepository.getAddressBook().keySet());
    }


}
