package lk.ijse.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    private String mem_id;
    private String mem_name;
    private String address;
    private int tel;
    private String user_name;


}
