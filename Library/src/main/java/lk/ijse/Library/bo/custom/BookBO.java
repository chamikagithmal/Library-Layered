package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.dto.BookOrdersDto;
import lk.ijse.Library.dto.tm.BookTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface BookBO extends SuperBO {

    boolean updateQty(List<BookOrdersDto> orderDetailDTOS) throws SQLException;

    boolean saveds(BookOrdersDto bookload) throws SQLException;

    boolean saveBook(BookDto dto) throws SQLException;

    boolean updateBook(final BookDto dto) throws SQLException;

    boolean deleteBook(String book_id) throws SQLException;

    List<BookDto> getAllBook() throws SQLException;

    BookDto SEarchBook(String book_id) throws SQLException;

    List<BookDto> SearchBookByAuthor(String author) throws SQLException;
}
