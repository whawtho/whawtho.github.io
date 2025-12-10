package AppointmentService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Five | 6-1 Project One

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AppointmentTest {
    
    @Test
    public void testValidAppointment() {
        Date currentDate = new Date(System.currentTimeMillis());
        Date afterToday = new Date(currentDate.getTime() + 10000); // 10 milliseconds in the future
        Appointment appointment = new Appointment("12345", afterToday, "Description");

        assertTrue(appointment.getAppointmentDate().compareTo(afterToday) == 0);
        assertTrue(appointment.getAppointmentId().equals("12345"));
        assertTrue(appointment.getDescription().equals("Description"));
    }

    @Test
    public void testInvalidAppointmentId() {
        Date currentDate = new Date(System.currentTimeMillis());
        Date afterToday = new Date(currentDate.getTime() + 10000); // 10 milliseconds in the future
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, afterToday, "Description");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", afterToday, "Description");
        });
    }

    @Test
    public void testInvalidAppointmentDate() {
        Date pastDate = new Date(System.currentTimeMillis() - 10000); // 10 milliseconds in the past
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", pastDate, "Description");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", null, "Description");
        });
    }

    @Test
    public void testInvalidDescription() {
        Date currentDate = new Date(System.currentTimeMillis());
        Date afterToday = new Date(currentDate.getTime() + 10000); // 10 milliseconds in the future
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", afterToday, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345", afterToday, "This description is definitely longer than fifty characters, which is not allowed.");
        });
    }
}