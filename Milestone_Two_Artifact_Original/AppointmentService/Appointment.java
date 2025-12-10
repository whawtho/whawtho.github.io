package AppointmentService;
//Zeb Hawthorne | 6-14-2024 | CS-320 | Module Five | 6-1 Project One

import java.util.Date;

public class Appointment {
    private final String appointmentId;
    private Date appointmentDate;
    private String description;

    public Appointment(String appointmentID, Date appointmentDate, String description) {
        if (appointmentID == null || appointmentID.length() > 10) {
            throw new IllegalArgumentException("Invalid ID");
        }
        this.appointmentId = appointmentID;

        setAppointmentDate(appointmentDate);
        setDescription(description);
    }

    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Invalid Appointment Date");
        }
        this.appointmentDate = appointmentDate;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Invalid Description");
        }
        this.description = description;
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
}