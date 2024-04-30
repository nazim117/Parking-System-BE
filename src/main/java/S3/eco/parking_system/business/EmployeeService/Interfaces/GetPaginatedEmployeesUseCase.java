package S3.eco.parking_system.business.EmployeeService.Interfaces;

import S3.eco.parking_system.domain.Employees.EmployeeData;
import org.springframework.data.domain.Page;

public interface GetPaginatedEmployeesUseCase {
    Page<EmployeeData> getEmployeesByPage(int pageNumber, int pageSize);
    Page<EmployeeData> getEmployeeByName(int pageNumber, int pageSize, String name);

}
