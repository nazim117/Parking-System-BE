package S3.eco.parking_system.business.EmployeeService.Impl;

import S3.eco.parking_system.business.EmployeeService.Exceptions.EmployeeNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Interfaces.DeleteEmployeesUseCase;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployeesUseCaseImpl implements DeleteEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with id: " + employeeId + " does not exist");
        }

        employeeRepository.deleteById(employeeId);

    }
}
