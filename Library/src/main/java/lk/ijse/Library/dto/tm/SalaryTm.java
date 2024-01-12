package lk.ijse.Library.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SalaryTm {

    private String s_id;
    private int amount;
    private String month;
    private String ot_bonuses;
    private String emp_id;
}
