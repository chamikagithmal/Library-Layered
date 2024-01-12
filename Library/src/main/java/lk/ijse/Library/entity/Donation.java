package lk.ijse.Library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Donation {

    private String d_id;
    private String d_name;
    private String address;
    private int tel;
    private int monetary_amount;


}
