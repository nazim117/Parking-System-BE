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

    public static final String APPOINTMENT_REMINDER_EMPLOYEE_SUBJECT = "Client is here";

    public static final String APPOINTMENT_REMINDER_EMPLOYEE_BODY = """
            Hello ${employeeName},
            Your client ${guestName} has arrived
            Your meeting is at ${meetingTime} on ${meetingDate}
            Get ready!
            """;

    public static final String APPOINTMENT_NO_PARKING_SPACE_SUBJECT = "No parking available";

    public static final String APPOINTMENT_NO_PARKING_SPACE_BODY = """
            Hello ${guestName},
            
            We do not have any available parking space at the moment
            Go to <a href="https://www.google.com/maps/search/?api=1&query=Esp, 5633 AJ Eindhoven" target="_blank">Esp, 5633 AJ Eindhoven</a>
            """;
}
