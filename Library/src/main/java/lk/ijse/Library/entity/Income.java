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
public class Income {

    private String ic_id;
    private int amount;
    private Date date;
    private String mem_id;
}
