package S3.eco.parking_system.business;

public interface EmailSenderService {
    public void sendEmail(String toEmail, String subject, String body);
}
