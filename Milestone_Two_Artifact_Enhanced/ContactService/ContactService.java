package ContactService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Module Three | 6-1 Project One

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;          // ENHANCEMENT: needed for returning search results
import java.util.List;              // ENHANCEMENT: interface for list results

public class ContactService {
	private final Map<String, Contact> contacts;

    public ContactService() {
        this.contacts = new HashMap<>();
    }

    public void addContact(Contact contact) {
        if (contact != null && !contacts.containsKey(contact.getcontactId())) {
            contacts.put(contact.getcontactId(), contact);
        }
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public void deleteContact(String contactId) {
        contacts.remove(contactId);
    }

    public void updateContactFirstName(String contactId, String firstName) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.setfirstName(firstName);
        }
    }

    public void updateContactLastName(String contactId, String lastName) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.setlastName(lastName);
        }
    }

    public void updateContactPhone(String contactId, String phoneNumber) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.setphoneNumber(phoneNumber);
        }
    }

    public void updateContactAddress(String contactId, String address) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.setaddress(address);
        }
    }

    // ENHANCEMENT: Search contacts by first or last name (case-insensitive)
    public List<Contact> searchByName(String query) {
        List<Contact> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return results;
        }

        String q = query.toLowerCase();

        for (Contact c : contacts.values()) {
            if (c.getfirstName().toLowerCase().contains(q) ||
                c.getlastName().toLowerCase().contains(q)) {
                results.add(c);
            }
        }
        return results;
    }

    // ENHANCEMENT: Search contacts by phone number (exact match)
    public List<Contact> searchByPhone(String phoneQuery) {
        List<Contact> results = new ArrayList<>();
        if (phoneQuery == null || phoneQuery.trim().isEmpty()) {
            return results;
        }

        for (Contact c : contacts.values()) {
            if (c.getphoneNumber().equals(phoneQuery)) {
                results.add(c);
            }
        }
        return results;
    }
    
 // ENHANCEMENT: Combined search method for contacts
    public List<Contact> searchContacts(String query) {
        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts.values()) {
            if (contact.getfirstName().equalsIgnoreCase(query) ||
                contact.getlastName().equalsIgnoreCase(query) ||
                contact.getphoneNumber().contains(query) ||
                contact.getaddress().toLowerCase().contains(query.toLowerCase())) 
            {
                results.add(contact);
            }
        }
        return results;
    }

}
