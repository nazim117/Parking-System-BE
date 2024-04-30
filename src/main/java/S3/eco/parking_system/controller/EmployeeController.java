package S3.eco.parking_system.controller;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Exceptions.*;
import S3.eco.parking_system.business.EmployeeService.Interfaces.*;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import S3.eco.parking_system.domain.Employees.CreateEmployeeRequest;
import S3.eco.parking_system.domain.Employees.EditEmployeeRequest;
import S3.eco.parking_system.domain.Employees.EmployeeData;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final GetPaginatedEmployeesUseCase getPaginatedEmployeesUseCase;
    private final GetEmployeesUseCase getEmployeesUseCase;
    private final CreateEmployeesUseCase createEmployeesUseCase;
    private final DeleteEmployeesUseCase deleteEmployeesUseCase;
    private final EditEmployeeUseCase editEmployeeUseCase;

    @GetMapping("/pages")
    public ResponseEntity<Page<EmployeeData>> getAppointmentPage(@RequestParam(value = "page") Integer page,
                                                                 @RequestParam(value = "pageSize") Integer pageSize) {
        try {
            Page<EmployeeData> employeePage = getPaginatedEmployeesUseCase.getEmployeesByPage(page, pageSize);
            return new ResponseEntity<>(employeePage, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(getPaginatedEmployeesUseCase.getEmployeesByPage(0, pageSize), HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EmployeeData> getEmployeeById(@PathVariable Long id) {
        try{
            EmployeeData employeeData = getEmployeesUseCase.getEmployeeById(id);
            return new ResponseEntity<>(employeeData, HttpStatus.OK);
        }catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/search_by_name")
    public ResponseEntity<Page<EmployeeData>> getEmployeeByName(@RequestParam(value = "page") Integer page,
                                                                        @RequestParam(value = "pageSize") Integer pageSize,
                                                                        @RequestParam(value = "name") String name) {
        try {
            Page<EmployeeData> employeesPage = getPaginatedEmployeesUseCase.getEmployeeByName(page, pageSize, name);
            return new ResponseEntity<>(employeesPage, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(getPaginatedEmployeesUseCase.getEmployeeByName(0, pageSize, ""), HttpStatus.OK);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeData> getEmployeeByEmail(@PathVariable String email) {
        try {
            EmployeeData employee = getEmployeesUseCase.getEmployeeByEmail(email);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest request) {
        try {
            Long employeeId = createEmployeesUseCase.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeId);
        } catch (EmployeeAlreadyExistsException | EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            deleteEmployeesUseCase.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editEmployee(@PathVariable Long id, @RequestBody EditEmployeeRequest request) {
        try {
            editEmployeeUseCase.editEmployee(id, request);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.notFound().header("X-Error-Message", "Employee not found.").build();
        }
    }
}
