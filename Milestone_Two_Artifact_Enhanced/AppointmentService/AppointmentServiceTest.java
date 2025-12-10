package AppointmentService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Appointment Service Tests (Enhanced)

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    private AppointmentService service;
    private Appointment appt1;
    private Appointment appt2;

    private Date futureDate(int daysFromNow) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, daysFromNow);
        return cal.getTime();
    }

    @BeforeEach
    public void setup() {
        service = new AppointmentService();
        appt1 = new Appointment("A1", futureDate(5), "Dentist cleaning");
        appt2 = new Appointment("A2", futureDate(7), "Eye exam follow-up");
        service.addAppointment(appt1);
        service.addAppointment(appt2);
    }

    @Test
    public void testAddAppointmentSuccess() {
        Appointment a3 = new Appointment("A3", futureDate(10), "Physical");
        service.addAppointment(a3);
        assertNotNull(service.getAppointment("A3"));
    }

    @Test
    public void testAddAppointmentDuplicateFailure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addAppointment(new Appointment("A1", futureDate(3), "Duplicate"));
        });
    }

    @Test
    public void testDeleteAppointmentSuccess() {
        service.deleteAppointment("A1");
        assertNull(service.getAppointment("A1"));
    }

    // ---------------- ENHANCEMENT TESTS BELOW ----------------

    @Test
    public void testSearchByDescriptionSuccess() { // ENHANCEMENT
        List<Appointment> results = service.searchByDescription("dentist");
        assertEquals(1, results.size());
        assertEquals("A1", results.get(0).getAppointmentId());
    }

    @Test
    public void testSearchByDescriptionNoMatch() { // ENHANCEMENT
        List<Appointment> results = service.searchByDescription("surgery");
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchByDateSuccess() { // ENHANCEMENT
        Date targetDate = appt1.getAppointmentDate();
        List<Appointment> results = service.searchByDate(targetDate);
        assertEquals(1, results.size());
        assertEquals("A1", results.get(0).getAppointmentId());
    }

    @Test
    public void testSearchByDateNoMatch() { // ENHANCEMENT
        Date differentDate = futureDate(30);
        List<Appointment> results = service.searchByDate(differentDate);
        assertTrue(results.isEmpty());
    }
}
