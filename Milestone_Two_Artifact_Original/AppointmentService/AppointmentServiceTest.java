package AppointmentService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Five | 6-1 Project One

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    public void setUp() {
        service = new AppointmentService();
    }

    @Test
    public void testAddAppointment() {
        Date currentDate = new Date(System.currentTimeMillis());
        Date afterToday = new Date(currentDate.getTime() + 10000); // 10 milliseconds in the future
        Appointment appointment = new Appointment("12345", afterToday, "Description");

        service.addAppointment(appointment);

        // verify the appointment details
        assertEquals("12345", appointment.getAppointmentId());
        assertEquals(afterToday, appointment.getAppointmentDate());
        assertEquals("Description", appointment.getDescription());

        // check if the appointment can be retrieved by its ID
        assertEquals(appointment, service.getAppointment("12345"));
    }


    @Test
    public void testUpdateAppointmentDate() {
        Date futureDate = new Date(System.currentTimeMillis() + 10000);
        Date newFutureDate = new Date(System.currentTimeMillis() + 20000);
        Appointment appointment = new Appointment("12345", futureDate, "Description");
        service.addAppointment(appointment);
        service.updateAppointmentDate("12345", newFutureDate);
        Appointment updatedAppointment = service.getAppointment("12345");
        assertEquals(newFutureDate, updatedAppointment.getAppointmentDate());
    }

    @Test
    public void testUpdateAppointmentDateToInvalidDate() {
        Date futureDate = new Date(System.currentTimeMillis() + 10000);
        Date pastDate = new Date(System.currentTimeMillis() - 10000);
        Appointment appointment = new Appointment("12345", futureDate, "Description");
        service.addAppointment(appointment);
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointmentDate("12345", pastDate);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateAppointmentDate("12345", null);
        });
    }

    @Test
    public void testDeleteAppointment() {
        Date futureDate = new Date(System.currentTimeMillis() + 10000);
        Appointment appointment = new Appointment("12345", futureDate, "Description");
        service.addAppointment(appointment);
        service.deleteAppointment("12345");
        assertNull(service.getAppointment("12345"));
    }
}