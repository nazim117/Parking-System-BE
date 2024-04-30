package S3.eco.parking_system.business.EmployeeService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Exceptions.EmployeeNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Interfaces.GetEmployeesUseCase;
import S3.eco.parking_system.business.EmployeeService.Utils.ConverterEmployeesData;
import S3.eco.parking_system.domain.Employees.EmployeeData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEmployeesUseCaseImpl implements GetEmployeesUseCase {
    private final EmployeeRepository employeeRepository;
    private final ConverterEmployeesData converter;

    @Override
    public List<EmployeeData> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

        if (employeeEntities.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found");
        }

        return converter.convertToEmployeeData(employeeEntities);
    }

    @Override
    public EmployeeData getEmployeeById(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        EmployeeEntity employeeEntity = optionalEmployee.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
        return converter.convertToEmployeeData(employeeEntity);
    }

    @Override
    public EmployeeData getEmployeeByEmail(String email) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findByEmployeeEmail(email);
        EmployeeEntity employeeEntity = employeeOptional.orElseThrow(() -> new AppointmentNotFoundException("Employee not found for email: " + email));
        return converter.convertToEmployeeData(employeeEntity);
    }

}
