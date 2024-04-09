package S3.eco.parking_system.business.NotificationsService;

public interface EmailSenderUseCase {
    public void sendEmail(String toEmail, String subject, String body);
}
