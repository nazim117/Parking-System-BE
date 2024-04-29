package S3.eco.parking_system.controller;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Interfaces.GetPaginatedEmployeesUseCase;
import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.domain.Employees.EmployeeData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final GetPaginatedEmployeesUseCase getPaginatedEmployeesUseCase;

    @GetMapping("/pages")
    public ResponseEntity<Page<EmployeeData>> getAppointmentPage(@RequestParam(value = "page") Integer page,
                                                                 @RequestParam(value = "pageSize") Integer pageSize) {
        try {
            Page<EmployeeData> employeePage = getPaginatedEmployeesUseCase.getEmployeesByPage(page, pageSize);
            return new ResponseEntity<>(employeePage, HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(getPaginatedEmployeesUseCase.getEmployeesByPage(0, pageSize), HttpStatus.OK);
        }
    }
}
