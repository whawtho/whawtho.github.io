package AppointmentService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Appointment Unit Tests

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Date futureDate(int daysFromNow) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, daysFromNow);
        return cal.getTime();
    }

    @Test
    public void testAppointmentCreationSuccess() {
        Appointment a = new Appointment("A123", futureDate(5), "Doctor visit");
        assertEquals("A123", a.getAppointmentId());
        assertEquals("Doctor visit", a.getDescription());
    }

    @Test
    public void testAppointmentIdFailureTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A12345678901", futureDate(5), "Doctor visit");
        });
    }

    @Test
    public void testAppointmentDateFailureNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", null, "Doctor visit");
        });
    }

    @Test
    public void testAppointmentDateFailurePastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -2);
        Date past = cal.getTime();

        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", past, "Doctor visit");
        });
    }

    @Test
    public void testDescriptionFailureTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("A123", futureDate(3),
                "This description is way too long to be valid because it exceeds fifty characters");
        });
    }
}
