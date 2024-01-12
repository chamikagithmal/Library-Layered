package lk.ijse.Library.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DonationTm {

    private String d_id;
    private String d_name;
    private String address;
    private int tel;
    private int monetary_amount;

}

