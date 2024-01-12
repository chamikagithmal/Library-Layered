package lk.ijse.Library.dao.custom;

import lk.ijse.Library.dao.CrudDAO;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.dto.BookOrdersDto;
import lk.ijse.Library.dto.tm.BookTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BookDAO extends CrudDAO<BookDto> {
      boolean updateQty(List<BookOrdersDto> orderDetailDTOS) throws SQLException;

      List<BookDto> SearchBookByAuthor(String author) throws SQLException;

       boolean saveds(BookOrdersDto bookload) throws SQLException;
}
