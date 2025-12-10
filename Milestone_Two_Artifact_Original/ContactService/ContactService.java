package ContactService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Three | 6-1 Project One

import java.util.HashMap;
import java.util.Map;

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
}
