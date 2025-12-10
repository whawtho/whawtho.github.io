package ContactService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Contact Management

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phoneNumber, String address) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid ID");
        }
        this.contactId = contactId;

        setfirstName(firstName);
        setlastName(lastName);
        setphoneNumber(phoneNumber);
        setaddress(address);
    }

    public void setfirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid First Name");
        }
        this.firstName = firstName;
    }

    public void setlastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid Last Name");
        }
        this.lastName = lastName;
    }

    public void setphoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid Phone Number");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setaddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid Address");
        }
        this.address = address;
    }

    public String getcontactId() {
        return contactId;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }

    public String getaddress() {
        return address;
    }
}
