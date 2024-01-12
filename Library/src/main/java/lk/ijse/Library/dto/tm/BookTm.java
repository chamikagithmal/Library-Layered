package lk.ijse.Library.dto.tm;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTm {
    private String book_id;
    private String book_name;
    private int qty;
    private String author;
    private String catagory;
    private String languege;
    private String d_id;
    private String s_id;


}
