package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.BookOrdersDto;

import java.sql.SQLException;
import java.util.List;

public interface BookOrderBO extends SuperBO {
    boolean pleaceOrder(List<BookOrdersDto> orderDetailDTOS) throws SQLException;

    }
