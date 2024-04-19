package S3.eco.parking_system.controller;

import S3.eco.parking_system.business.NotificationsService.Interfaces.EmailSenderUseCase;
import S3.eco.parking_system.utils.EmailMessages;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {
    private final EmailSenderUseCase emailSenderService;

    // testing method
    @GetMapping("sendEmail")
    public void sendEmail(){
        emailSenderService.sendEmail(getEmail(), EmailMessages.APPOINTMENT_CONFIRMATION_SUBJECT, EmailMessages.APPOINTMENT_CONFIRMATION_BODY);
    }

    private String getEmail(){
        return "dansil2301@gmail.com";
    }
}
