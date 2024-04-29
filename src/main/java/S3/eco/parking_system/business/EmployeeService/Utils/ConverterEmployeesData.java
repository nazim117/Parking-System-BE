package S3.eco.parking_system.business.EmployeeService.Utils;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.domain.Employees.EmployeeData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterEmployeesData {
    public EmployeeData convertToEmployeeData(EmployeeEntity entity) {
        return EmployeeData.builder()
                .id(entity.getId())
                .name(entity.getEmployeeName())
                .email(entity.getEmployeeEmail())
                .build();
    }

    public List<EmployeeData> convertToEmployeeData(List<EmployeeEntity> entities) {
        List<EmployeeData> employeeDataLst = new ArrayList<>();

        for (EmployeeEntity entity : entities) {
            employeeDataLst.add(EmployeeData.builder()
                    .id(entity.getId())
                    .name(entity.getEmployeeName())
                    .email(entity.getEmployeeEmail())
                    .build());
        }

        return employeeDataLst;
    }
}
