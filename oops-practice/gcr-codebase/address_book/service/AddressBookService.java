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
    // UC 5: Ability to create multiple contact

    public void addContactToBook(String bookName, Contact contact) {
        try {
            // UC 7: Ability to ensure unique contacts in a particular address book
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
                    addressBookRepository.deleteContact(bookName, contact);
                    System.out.println("Contact Deleted Successfully");
                    return;
                }
            }
            throw new Exception("Contact Not Found");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // UC 6: Ability to create multiple unique address books
    public void addNewBook(String bookName) {
        try {
            for(String name:getAddressBookNames()){
                if(name.equalsIgnoreCase(bookName)){
                    throw new Exception("Book Already Exists");
                }
            
            }
            addressBookRepository.addNewBook(bookName);
            System.out.println("Book Added Successfully");
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // UC 8: Ability to get contant in a particular city
    public List<Contact> getContactsByCity(String city) {
        List<Contact> contactsList = new ArrayList<>();
        for (List<Contact> contactList : addressBookRepository.getAddressBook().values()) {
            for(Contact contact:contactList){
                if(contact.getCity().equalsIgnoreCase(city)){
                    contactsList.add(contact);
                }
            }
        }
        return contactsList;

    }
    // UC 8: Ability to get contant in a particular state
    public List<Contact> getContactsByState(String state) {
        List<Contact> contactsList = new ArrayList<>();
        for(List<Contact> contactList:addressBookRepository.getAddressBook().values()){
            for(Contact contact:contactList){
                if(contact.getState().equalsIgnoreCase(state)){
                    contactsList.add(contact);
                }
            }
        }
        return contactsList;
    }
    

    public Map<String, List<Contact>> getAllContacts(){
        return addressBookRepository.getAddressBook();
    }

    public List<String> getAddressBookNames(){
        return new ArrayList<>(addressBookRepository.getAddressBook().keySet());
    }


}
