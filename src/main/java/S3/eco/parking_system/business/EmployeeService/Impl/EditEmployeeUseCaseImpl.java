package S3.eco.parking_system.business.EmployeeService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Exceptions.EmployeeNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Interfaces.EditEmployeeUseCase;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import S3.eco.parking_system.domain.Employees.EditEmployeeRequest;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditEmployeeUseCaseImpl implements EditEmployeeUseCase {
    private final EmployeeRepository employeeRepository;
    @Override
    public void editEmployee(Long employeeId, EditEmployeeRequest request) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(employeeId);
        EmployeeEntity employeeEntity = optionalEmployee
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + employeeId + " not found"));

        employeeEntity.setEmployeeEmail(request.getEmail());
        employeeEntity.setEmployeeName(request.getName());

        employeeRepository.save(employeeEntity);
    }
}
