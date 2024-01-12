package lk.ijse.Library.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookSearchTm {
    private String id;
    private String name;
    private int qty;
}
