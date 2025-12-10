package ContactService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Three | 6-1 Project One


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactTest {
    private Contact contact;

    @BeforeEach
    public void testContact() {
        contact = new Contact("ABCDE12345", "John", "Doe", "5555555555", "123 S Main St");
    }

    // Success Tests
    @Test
    public void testGetContactIdSuccess() {
        assertTrue(contact.getcontactId().equals("ABCDE12345"));
    }

    @Test
    public void testGetFirstNameSuccess() {
        assertTrue(contact.getfirstName().equals("John"));
    }

    @Test
    public void testGetLastNameSuccess() {
        assertTrue(contact.getlastName().equals("Doe"));
    }

    @Test
    public void testGetPhoneSuccess() {
        assertTrue(contact.getphoneNumber().equals("5555555555"));
    }

    @Test
    public void testGetAddressSuccess() {
        assertTrue(contact.getaddress().equals("123 S Main St"));
    }

    // Failure Tests
    @Test
    public void testContactIdFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("ABCDE1234567890", "John", "Doe", "5555555555", "123 S Main St");
        });
    }

    @Test
    public void testfirstNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setfirstName("JohnathanLongName");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setfirstName(null);
        });
    }

    @Test
    public void testLastNameFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setlastName("DoeLongLastName");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setlastName(null);
        });
    }

    @Test
    public void testPhoneNumberFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setphoneNumber("123456");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setphoneNumber("abcdefghij");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setphoneNumber(null);
        });
    }

    @Test
    public void testAddressFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setaddress("1234567890 A Very Too Long Street");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setaddress(null);
        });
    }
}
