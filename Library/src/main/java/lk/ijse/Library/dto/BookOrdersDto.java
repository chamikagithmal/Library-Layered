package lk.ijse.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookOrdersDto {
    private String mem_id;
    private String Book_id;
    private int Qty;
    private String date;
    private String reterndate;
}
