package lk.ijse.Library.dto;

import javafx.scene.control.DatePicker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeDto {

    private String emp_id;
    private String name;
    private String address;
    private int tel;
    private Date DOB;
    private String user_name;


}
