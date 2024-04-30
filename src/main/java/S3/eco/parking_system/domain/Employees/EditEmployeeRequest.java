package S3.eco.parking_system.domain.Employees;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditEmployeeRequest {
    private String name;
    private String email;
}
