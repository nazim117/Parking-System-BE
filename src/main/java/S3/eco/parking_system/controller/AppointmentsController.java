package S3.eco.parking_system.controller;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.*;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentAlreadyExistsException;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final GetPaginatedAppointmentsUseCase getPaginatedAppointmentsUseCase;

    @GetMapping()
    public ResponseEntity<List<AppointmentData>> getAllAppointment() {
        try {
            List<AppointmentData> appointments = getAppointmentsUseCase.getAll();
            return ResponseEntity.ok(appointments);
        } catch (AppointmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping("/pages")
    public ResponseEntity<Page<AppointmentData>> getAppointmentPage(@RequestParam(value = "page") Integer page,
                                                                    @RequestParam(value = "pageSize") Integer pageSize) {
        try {
            Page<AppointmentData> appointmentPage = getPaginatedAppointmentsUseCase.getAppointmentsByPage(page, pageSize);
            return new ResponseEntity<>(appointmentPage, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(getPaginatedAppointmentsUseCase.getAppointmentsByPage(0, pageSize), HttpStatus.OK);
        }
    }

    @GetMapping("/calendar_overview")
    public ResponseEntity<?> getAppointmentsByDate(@RequestParam(value = "year") Integer year,
                                                   @RequestParam(value = "month") Integer month) {
        try {
            List<AppointmentData> appointments = getAppointmentsUseCase.getAppointmentsByYearAndMonth(year, month);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>("No appointments found for year: " + year + " and month: " + month, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
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
