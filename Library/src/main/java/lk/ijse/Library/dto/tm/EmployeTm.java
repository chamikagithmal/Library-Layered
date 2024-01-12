package lk.ijse.Library.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeTm {

    private String emp_id;
    private String emp_name;
    private String address;
    private int tel;
    private Date DOB;
    private String user_name;
}
