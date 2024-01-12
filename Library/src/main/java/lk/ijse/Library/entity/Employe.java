package lk.ijse.Library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employe {

    private String emp_id;
    private String name;
    private String address;
    private int tel;
    private Date DOB;
    private String user_name;


}
