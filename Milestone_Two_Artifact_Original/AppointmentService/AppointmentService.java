package AppointmentService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Five | 6-1 Project One

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class AppointmentService {
	private final Map<String, Appointment> appointments;

    public AppointmentService() {
        this.appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment != null && !appointments.containsKey(appointment.getAppointmentId())) {
            appointments.put(appointment.getAppointmentId(), appointment);
        }
    }

    public Appointment getAppointment(String appointmentID) {
        return appointments.get(appointmentID);
    }

    public void deleteAppointment(String appointmentID) {
        appointments.remove(appointmentID);
    }
    public void updateAppointmentDate(String appointmentID, Date appointmentDate) {
        Appointment appointment = appointments.get(appointmentID);
        if (appointment != null) {
            appointment.setAppointmentDate(appointmentDate);
        }
    }

    public void updateTaskDescription(String appointmentID, String description) {
        Appointment appointment = appointments.get(appointmentID);
        if (appointment != null) {
            appointment.setDescription(description);
        }
    }
}
