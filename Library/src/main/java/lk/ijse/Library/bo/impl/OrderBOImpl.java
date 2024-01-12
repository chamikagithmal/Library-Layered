package lk.ijse.Library.bo.impl;

import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.BookOrdersDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl {
    public static boolean save(List<BookOrdersDto> orderDetailDTOS) throws SQLException {
        for (BookOrdersDto bookload : orderDetailDTOS) {
            if (!saved(bookload)) {
                return false;
            }
        }
        return true;
    }

    private static boolean saved(BookOrdersDto bookload) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO book_order VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, bookload.getMem_id());
        pstm.setString(2, bookload.getBook_id());
        pstm.setString(3, String.valueOf(bookload.getQty()));
        pstm.setString(4, String.valueOf(bookload.getDate()));
        pstm.setString(5, String.valueOf(bookload.getReterndate()));


        boolean isSaved = pstm.executeUpdate() > 0;
        System.out.println(isSaved);
        return isSaved;

    }
}
