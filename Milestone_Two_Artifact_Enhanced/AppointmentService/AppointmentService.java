package AppointmentService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Appointment Service (Enhanced Search)

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentService {
    private final List<Appointment> appointments;

    public AppointmentService() {
        appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null");
        }

        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointment.getAppointmentId())) {
                throw new IllegalArgumentException("Duplicate appointment ID");
            }
        }

        appointments.add(appointment);
    }

    public void deleteAppointment(String appointmentId) {
        appointments.removeIf(a -> a.getAppointmentId().equals(appointmentId));
    }

    public Appointment getAppointment(String appointmentId) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                return a;
            }
        }
        return null;
    }

    // ---------------- ENHANCEMENTS BELOW ----------------

    // ENHANCEMENT: search appointments by keyword in description (case-insensitive)
    public List<Appointment> searchByDescription(String keyword) {
        List<Appointment> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        String k = keyword.toLowerCase();
        for (Appointment a : appointments) {
            if (a.getDescription().toLowerCase().contains(k)) {
                results.add(a);
            }
        }
        return results;
    }

    // ENHANCEMENT: search appointments by exact date (ignores time)
    public List<Appointment> searchByDate(Date date) {
        List<Appointment> results = new ArrayList<>();

        if (date == null) {
            return results;
        }

        for (Appointment a : appointments) {
            if (isSameDay(a.getAppointmentDate(), date)) {
                results.add(a);
            }
        }
        return results;
    }

    // ENHANCEMENT helper: compare just year/month/day
    @SuppressWarnings("deprecation")
	private boolean isSameDay(Date d1, Date d2) {
        return d1.getYear() == d2.getYear()
            && d1.getMonth() == d2.getMonth()
            && d1.getDate() == d2.getDate();
    }
}
