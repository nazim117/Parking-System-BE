package S3.eco.parking_system.business.EmployeeService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.AppointmentsService.Utils.ConverterAppointmentsData;
import S3.eco.parking_system.business.EmployeeService.Interfaces.GetPaginatedEmployeesUseCase;
import S3.eco.parking_system.business.EmployeeService.Utils.ConverterEmployeesData;
import S3.eco.parking_system.domain.Employees.EmployeeData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPaginatedEmployeesUseCaseImpl implements GetPaginatedEmployeesUseCase {
    private final EmployeeRepository employeeRepository;
    private final ConverterEmployeesData converter;

    @Override
    public Page<EmployeeData> getEmployeesByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<EmployeeEntity> employeePage = employeeRepository.findAllByOrderByEmployeeName(pageable);

        if (employeePage.isEmpty()) {
            throw new AppointmentNotFoundException("No employees found on page: " + pageNumber);
        }

        return employeePage.map(converter::convertToEmployeeData);
    }

    @Override
    public Page<EmployeeData> getEmployeeByName(int pageNumber, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<EmployeeEntity> employeePage = employeeRepository.findAllByEmployeeName(name, pageable);

        if (employeePage.isEmpty()) {
            throw new AppointmentNotFoundException("No employees found on page: " + pageNumber);
        }

        return employeePage.map(converter::convertToEmployeeData);
    }
}
