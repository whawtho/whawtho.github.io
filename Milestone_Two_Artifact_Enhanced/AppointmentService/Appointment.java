package AppointmentService;
//Zeb Hawthorne | CS-499 | CS-320 Artifact | Appointment Management

import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException("Invalid Appointment ID");
        }
        this.appointmentId = appointmentId;

        setAppointmentDate(appointmentDate);
        setDescription(description);
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date cannot be null");
        }

        Date today = new Date();
        if (appointmentDate.before(today)) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }

        this.appointmentDate = appointmentDate;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid description");
        }
        this.description = description;
    }
}
