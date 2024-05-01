package S3.eco.parking_system.business.NotificationsService.Interfaces;

public interface EmailSenderUseCase {
    void sendEmail(String toEmail, String subject, String body);
}
