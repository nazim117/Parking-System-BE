package S3.eco.parking_system.business.EmployeeService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import S3.eco.parking_system.domain.Employees.EditEmployeeRequest;

public interface EditEmployeeUseCase {
    void editEmployee(Long employeeId, EditEmployeeRequest request);

}
