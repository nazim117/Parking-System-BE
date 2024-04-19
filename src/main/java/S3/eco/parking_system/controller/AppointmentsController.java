package S3.eco.parking_system.controller;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.CreateAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Interfaces.DeleteAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Interfaces.EditAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Interfaces.GetAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentAlreadyExistsException;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointments")
@AllArgsConstructor
public class AppointmentsController {
    private final GetAppointmentsUseCase getAppointmentsUseCase;
    private final CreateAppointmentsUseCase createAppointmentsUseCase;
    private final DeleteAppointmentsUseCase deleteAppointmentsUseCase;
    private final EditAppointmentsUseCase editAppointmentsUseCase;

    @GetMapping()
    public ResponseEntity<List<AppointmentData>> getAllAppointment() {
        try {
            List<AppointmentData> appointments = getAppointmentsUseCase.getAll();
            return ResponseEntity.ok(appointments);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentData> getAppointmentById(@PathVariable Long id) {
        try {
            AppointmentData appointment = getAppointmentsUseCase.getById(id);
            return ResponseEntity.ok(appointment);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<Long> createAppointment(@RequestBody CreateAppointmentRequest request) {
        try {
            Long appointmentId = createAppointmentsUseCase.createAppointment(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentId);
        } catch (AppointmentAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            deleteAppointmentsUseCase.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editAppointment(@PathVariable Long id, @RequestBody EditAppointmentRequest request) {
        try {
            editAppointmentsUseCase.editAppointment(id, request);
            return ResponseEntity.noContent().build();
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
