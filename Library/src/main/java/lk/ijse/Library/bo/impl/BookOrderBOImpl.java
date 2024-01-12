package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.BookBO;
import lk.ijse.Library.bo.custom.BookOrderBO;
import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.BookOrdersDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookOrderBOImpl implements BookOrderBO {
    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    public  boolean pleaceOrder(List<BookOrdersDto> orderDetailDTOS) throws SQLException {
        Connection con = null;

        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = OrderBOImpl.save(orderDetailDTOS);
            if (isSaved) {
                System.out.println(orderDetailDTOS.get(0).getBook_id());
                boolean isUpdated = bookBO.updateQty(orderDetailDTOS);


                    if (isUpdated) {
                       con.commit();
                       return true;
                    }

            }

        } catch (Exception e) {
            e.printStackTrace();
                con.rollback();

        } finally {
            con.setAutoCommit(true);
        }
        return false;
    }

}
