package S3.eco.parking_system.utils;

public class EmailMessages {
    public static final String APPOINTMENT_CONFIRMATION_SUBJECT = "Appointment confirmation";

    public static final String APPOINTMENT_CONFIRMATION_BODY = """
            Hello ${guestName},

            Thank you for scheduling an appointment with us.
            Your appointment is scheduled for
            ${date} at ${time}.
            You scheduled an appointment with ${employeeName}
            If you have any questions or need to reschedule, please contact us on siouxsecretary@gmail.com.
            We look forward to seeing you!

            Sioux Technologies""";

    public static final String APPOINTMENT_REMINDER_SUBJECT = "Appointment reminder";

    public static final String APPOINTMENT_REMINDER_BODY = "Hello (guest name), just a friendly reminder about your appointment on (date) at (time)";

    public static final String APPOINTMENT_ASSISTANCE_SUBJECT = "Assistance notification";

    public static final String APPOINTMENT_ASSISTANCE_BODY = "(Here guest will get notif if we have enough space for his transport)";
}
