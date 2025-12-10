package ContactService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Three | 6-1 Project One

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService contactService;
    private Contact contact;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
        contact = new Contact("ABCDE12345", "John", "Doe", "5555555555", "123 S Main St");
        contactService.addContact(contact);
    }

    @Test
    public void testAddContactIDSuccess() {
        Contact newContact = new Contact("0987654321", "Jane", "Doe", "0987654321", "789 Sesame St");
        contactService.addContact(newContact);
        assertTrue(contact != null);
        assertTrue(contact.getcontactId().equals("0987654321"));  
        assertTrue(contact.getfirstName().equals("Jane"));
        assertTrue(contact.getlastName().equals("Doe"));
        assertTrue(contact.getphoneNumber().equals("0987654321"));
        assertTrue(contact.getphoneNumber().equals("789 Sesame St"));
        }
    @Test
    public void testAddContactIDFail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Contact newContact = new Contact("09876543210", "Jane", "Doe", "0987654321", "789 Sesame St");
            contactService.addContact(newContact);
        });
    }

    @Test
    public void testGetContact() {
        assertEquals(contact, contactService.getContact("ABCDE12345"));
    }

    @Test
    public void testDeleteContact() {
        contactService.deleteContact("ABCDE12345");
        assertNull(contactService.getContact("ABCDE12345"));
    }
    @Test
    public void testUpdateContactFirstNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);

        contactService.updateContactFirstName("1", "Jane");
        Contact updatedContact = contactService.getContact("1");
        Assertions.assertNotNull(updatedContact);
        Assertions.assertTrue(updatedContact.getfirstName().equals("Jane"));
    }

    @Test
    public void testUpdateContactFirstNameFailure() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContactFirstName("999", "Jane");
        });
    }

    @Test
    public void testUpdateContactLastNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);

        contactService.updateContactLastName("1", "Smith");
        Contact updatedContact = contactService.getContact("1");
        Assertions.assertNotNull(updatedContact);
        Assertions.assertTrue(updatedContact.getlastName().equals("Smith"));
    }

    @Test
    public void testUpdateContactLastNameFailure() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContactLastName("999", "Smith");
        });
    }

    @Test
    public void testUpdateContactPhoneSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);

        contactService.updateContactPhone("1", "0987654321");
        Contact updatedContact = contactService.getContact("1");
        Assertions.assertNotNull(updatedContact);
        Assertions.assertTrue(updatedContact.getphoneNumber().equals("0987654321"));
    }

    @Test
    public void testUpdateContactPhoneFailure() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContactPhone("999", "0987654321");
        });
    }

    @Test
    public void testUpdateContactAddressSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);

        contactService.updateContactAddress("1", "456 Elm St");
        Contact updatedContact = contactService.getContact("1");
        Assertions.assertNotNull(updatedContact);
        Assertions.assertTrue(updatedContact.getaddress().equals("456 Elm St"));
    }

    @Test
    public void testUpdateContactAddressFailure() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contactService.updateContactAddress("999", "456 Elm St");
        });
    }

}

