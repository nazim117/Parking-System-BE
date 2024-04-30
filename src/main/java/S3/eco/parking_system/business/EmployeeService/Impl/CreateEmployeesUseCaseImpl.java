package S3.eco.parking_system.business.EmployeeService.Impl;

import S3.eco.parking_system.business.EmployeeService.Exceptions.*;
import S3.eco.parking_system.business.EmployeeService.Interfaces.CreateEmployeesUseCase;
import S3.eco.parking_system.domain.Employees.CreateEmployeeRequest;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CreateEmployeesUseCaseImpl implements CreateEmployeesUseCase {
    private final EmployeeRepository employeeRepository;

    @Override
    public Long createEmployee(CreateEmployeeRequest request) {
        Optional<EmployeeEntity> employee = employeeRepository.findByEmployeeEmail(request.getEmail());

        if (employee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee with email " + request.getEmail() + " already exists");
        }
        EmployeeEntity employeeEntity = saveNewEmployee(request);
        return employeeEntity.getId();
    }

    private EmployeeEntity saveNewEmployee(CreateEmployeeRequest request) {
        EmployeeEntity newEmployee = EmployeeEntity.builder()
                .employeeName(request.getName())
                .employeeEmail(request.getEmail())
                .build();
        return employeeRepository.save(newEmployee);
    }
}
