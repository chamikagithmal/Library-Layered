package lk.ijse.Library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String id;
    private String name;
    private int qty;
    private String author;
    private String catagory;
    private String languege;
    private String d_id;
    private String s_id;


}
