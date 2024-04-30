package S3.eco.parking_system.business.EmployeeService.Interfaces;

import S3.eco.parking_system.domain.Employees.EmployeeData;

import java.util.List;

public interface GetEmployeesUseCase {
    List<EmployeeData> getAllEmployees();
    EmployeeData getEmployeeById(Long id);
    EmployeeData getEmployeeByEmail(String email);
}
