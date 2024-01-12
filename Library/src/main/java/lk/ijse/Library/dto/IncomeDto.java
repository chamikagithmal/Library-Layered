package lk.ijse.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class IncomeDto {

    private String ic_id;
    private String amount;
    private Date date;
    private String mem_id;

}
