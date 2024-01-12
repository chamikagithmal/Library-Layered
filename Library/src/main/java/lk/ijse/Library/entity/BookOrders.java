package lk.ijse.Library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookOrders {
    private String mem_id;
    private String Book_id;
    private int Qty;
    private String date;
    private String reterndate;
}
