package S3.eco.parking_system.utils;

public class EmailMessages {
    public static final String APPOINTMENT_CONFIRMATION_SUBJECT = "Appointment confirmation";

    public static final String APPOINTMENT_CONFIRMATION_BODY = "Hello (name of the guest), your appointment is scheduled for (date) at (time).";

    public static final String APPOINTMENT_REMINDER_SUBJECT = "Appointment reminder";

    public static final String APPOINTMENT_REMINDER_BODY = "Hello (guest name), just a friendly reminder about your appointment on (date) at (time)";

    public static final String APPOINTMENT_ASSISTANCE_SUBJECT = "Assistance notification";

    public static final String APPOINTMENT_ASSISTANCE_BODY = "(Here guest will get notif if we have enough space for his transport)";
}
