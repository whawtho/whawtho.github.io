package ContactService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Contact Management

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;  // ENHANCEMENT: needed for search results

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContactSuccess() {
        Contact newContact = new Contact("1", "Jane", "Doe", "0987654321", "789 Sesame St");
        contactService.addContact(newContact);

        Contact stored = contactService.getContact("1");
        assertNotNull(stored);
        assertEquals("Jane", stored.getfirstName());
        assertEquals("Doe", stored.getlastName());
        assertEquals("0987654321", stored.getphoneNumber());
        assertEquals("789 Sesame St", stored.getaddress());
    }

    @Test
    public void testAddContactDuplicateIdFails() {
        Contact c1 = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        Contact c2 = new Contact("1", "Jane", "Smith", "0987654321", "456 Elm St");
        contactService.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> contactService.addContact(c2));
    }

    @Test
    public void testGetContact() {
        Contact contact = new Contact("ABCDE12345", "John", "Doe", "5555555555", "123 S Main St");
        contactService.addContact(contact);
        Contact result = contactService.getContact("ABCDE12345");
        assertNotNull(result);
        assertEquals("John", result.getfirstName());
    }

    @Test
    public void testDeleteContact() {
        Contact contact = new Contact("ABCDE12345", "John", "Doe", "5555555555", "123 S Main St");
        contactService.addContact(contact);
        contactService.deleteContact("ABCDE12345");
        assertNull(contactService.getContact("ABCDE12345"));
    }

    @Test
    public void testUpdateContactFirstNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContactFirstName("1", "Jane");
        assertEquals("Jane", contactService.getContact("1").getfirstName());
    }

    @Test
    public void testUpdateContactFirstNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactFirstName("999", "Jane"));
    }

    @Test
    public void testUpdateContactLastNameSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContactLastName("1", "Smith");
        assertEquals("Smith", contactService.getContact("1").getlastName());
    }

    @Test
    public void testUpdateContactLastNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactLastName("999", "Smith"));
    }

    @Test
    public void testUpdateContactPhoneSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContactPhone("1", "0987654321");
        assertEquals("0987654321", contactService.getContact("1").getphoneNumber());
    }

    @Test
    public void testUpdateContactPhoneFailure() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactPhone("999", "0987654321"));
    }

    @Test
    public void testUpdateContactAddressSuccess() {
        Contact contact = new Contact("1", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContactAddress("1", "456 Elm St");
        assertEquals("456 Elm St", contactService.getContact("1").getaddress());
    }

    @Test
    public void testUpdateContactAddressFailure() {
        assertThrows(IllegalArgumentException.class, () -> contactService.updateContactAddress("999", "456 Elm St"));
    }

    // -------------------------------------------------------------------------
    // ENHANCEMENT TESTS: Validate multi-field searchContacts().
    // -------------------------------------------------------------------------

    @Test
    public void testSearchContactsByFirstName() {
        contactService.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));
        contactService.addContact(new Contact("2", "Jane", "Smith", "0987654321", "456 Elm St"));
        contactService.addContact(new Contact("3", "Johnny", "Brown", "2223334444", "789 Oak St"));

        List<Contact> results = contactService.searchContacts("John"); // ENHANCEMENT
        assertEquals(2, results.size()); // ENHANCEMENT
    }

    @Test
    public void testSearchContactsByLastName() {
        contactService.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));
        contactService.addContact(new Contact("2", "Jane", "Doe", "0987654321", "456 Elm St"));

        List<Contact> results = contactService.searchContacts("Doe"); // ENHANCEMENT
        assertEquals(2, results.size()); // ENHANCEMENT
    }

    @Test
    public void testSearchContactsByPhoneFragment() {
        contactService.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));
        contactService.addContact(new Contact("2", "Jane", "Smith", "0987654321", "456 Elm St"));

        List<Contact> results = contactService.searchContacts("0987"); // ENHANCEMENT
        assertEquals(1, results.size()); // ENHANCEMENT
        assertEquals("2", results.get(0).getcontactId()); // ENHANCEMENT
    }

    @Test
    public void testSearchContactsEmptyQueryFails() {
        assertThrows(IllegalArgumentException.class, () -> contactService.searchContacts("   ")); // ENHANCEMENT
    }
}
