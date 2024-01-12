package lk.ijse.Library.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTm {

    private String mem_id;
    private String Book_id;
    private int Qty;
    private String date;
    private String reterndate;

}
