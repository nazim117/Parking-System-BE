package S3.eco.parking_system.business.EmployeeService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;
import S3.eco.parking_system.domain.Employees.CreateEmployeeRequest;

public interface CreateEmployeesUseCase {
    Long createEmployee(CreateEmployeeRequest request);

}
