package service;

import java.util.*;
import java.io.*;

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
    // UC 9: Ability to view contacts by city 
    public Map<String, List<Contact>> viewContactsByCity() {
        Map<String, List<Contact>> contactsMap = new HashMap<>();
        for (Map.Entry<String, List<Contact>> entry : addressBookRepository.getAddressBook().entrySet()) {
           for(Contact contact:entry.getValue()){
            String city=contact.getCity();
            if(!contactsMap.containsKey(city)){
                contactsMap.put(city,new ArrayList<>());
            }
            contactsMap.get(city).add(contact);
           }


        }
        return contactsMap;

    }
    // UC 9: Ability to view contacts by state
    public Map<String, List<Contact>> viewContactsByState() {
        Map<String, List<Contact>> contactsMap = new HashMap<>();
        for(Map.Entry<String, List<Contact>> entry:addressBookRepository.getAddressBook().entrySet()){
            for(Contact contact:entry.getValue()){
                String state=contact.getState();
                if(!contactsMap.containsKey(state)){
                    contactsMap.put(state,new ArrayList<>());
                }
                contactsMap.get(state).add(contact);
            }
                
        }
        return contactsMap;
    }
    // UC 10: Ability to count contacts by city
    public long countContactsByCity(String city) {
        long count = 0;
        for(Map.Entry<String, List<Contact>> entry:addressBookRepository.getAddressBook().entrySet()){
            for(Contact contact:entry.getValue()){
                if(contact.getCity().equalsIgnoreCase(city)){
                    count++;
                }
            }
        }
        return count;

    }
    // UC 10: Ability to count contacts by state
    public long countContactsByState(String state) {
        long count = 0;
        for(Map.Entry<String, List<Contact>> entry:addressBookRepository.getAddressBook().entrySet()){
            for(Contact contact:entry.getValue()){
                if(contact.getState().equalsIgnoreCase(state)){
                    count++;
                }
            }
        }
        return count;
    }

    // UC 11: Ability to sort the contacts in each address book by first name
    public void sortContactsByFirstName(boolean ascendingOrder) {
        Map<String, List<Contact>> addressBook = addressBookRepository.getAddressBook();
        for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
            List<Contact> contacts = entry.getValue();
            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact contact1, Contact contact2) {
                    if (ascendingOrder) {
                        return contact1.getFirstName().compareTo(contact2.getFirstName());
                    } else {
                        return contact2.getFirstName().compareTo(contact1.getFirstName());
                    }
                }
            });
        }
    }


    // UC 12: Ability to sort the contacts in each address book by city
    public void sortContactsByCity(boolean ascendingOrder) {
        Map<String,List<Contact>> addressBook=addressBookRepository.getAddressBook();
        for(Map.Entry<String, List<Contact>> entry:addressBook.entrySet()){
            List<Contact> contacts=entry.getValue();
            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact contact1, Contact contact2) {
                    if (ascendingOrder) {
                        return contact1.getCity().compareTo(contact2.getCity());
                    } else {
                        return contact2.getCity().compareTo(contact1.getCity());
                    }
                }
            });
        }
        
    }
    // UC 12: Ability to sort the contacts in each address book by state
    public void sortContactsByState(boolean ascendingOrder) {
        Map<String, List<Contact>> addressBook = addressBookRepository.getAddressBook();
        for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
            List<Contact> contacts = entry.getValue();
            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact contact1, Contact contact2) {
                    if (ascendingOrder) {
                      return contact1.getState().compareTo(contact2.getState());
                    } else {
                        return contact2.getState().compareTo(contact1.getState());

                    }
                }
            });
        }
    }
    // UC 12: Ability to sort the contacts in each address book by zip
    public void sortContactsByZip(boolean ascendingOrder) {
        Map<String, List<Contact>> addressBook = addressBookRepository.getAddressBook();
        for (Map.Entry<String, List<Contact>> entry : addressBook.entrySet()) {
            List<Contact> contacts = entry.getValue();
            Collections.sort(contacts, new Comparator<Contact>() {
                @Override
                public int compare(Contact contact1, Contact contact2) {
                    if (ascendingOrder) {
                        return contact1.getZip().compareTo(contact2.getZip());
                    } else {
                        return contact2.getZip().compareTo(contact1.getZip());
                    }
                }
            });
        }
    }

    public Map<String, List<Contact>> getAllContacts(){
        return addressBookRepository.getAddressBook();
    }

    public List<String> getAddressBookNames(){
        return new ArrayList<>(addressBookRepository.getAddressBook().keySet());
    }

    // UC 13: Ability to write address book to file
    public void saveToFile(String fileName) {
        try {
            addressBookRepository.writeToFile(fileName);
            System.out.println("Address Book saved to file successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // UC 13: Ability to read address book from file
    public void loadFromFile(String fileName) {
        try {
            addressBookRepository.readFromFile(fileName);
            System.out.println("Address Book loaded from file successfully: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // UC 14: Ability to write address book to CSV file
    public void saveToCSV(String fileName) {
        try {
            addressBookRepository.writeToCSV(fileName);
            if (!fileName.toLowerCase().endsWith(".csv")) {
                fileName += ".csv";
            }
            System.out.println("Address Book saved to CSV file successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    // UC 14: Ability to read address book from CSV file
    public void loadFromCSV(String fileName) {
        try {
            addressBookRepository.readFromCSV(fileName);
            if (!fileName.toLowerCase().endsWith(".csv")) {
                fileName += ".csv";
            }
            System.out.println("Address Book loaded from CSV file successfully: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found: " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading from CSV file: " + e.getMessage());
        }
    }

}
