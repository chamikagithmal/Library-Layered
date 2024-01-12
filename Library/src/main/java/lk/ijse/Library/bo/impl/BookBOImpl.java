package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.BookBO;
import lk.ijse.Library.dao.DAOFactory;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.BookDAO;
import lk.ijse.Library.dao.custom.impl.BookDAOImpl;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.db.DbConnection;
import lk.ijse.Library.dto.BookOrdersDto;
import lk.ijse.Library.dto.tm.BookTm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {


    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    public  boolean updateQty(List<BookOrdersDto> orderDetailDTOS) throws SQLException {
        for (BookOrdersDto bookload : orderDetailDTOS) {
            if (!saveds(bookload)) {
                return false;
            }
        }
        return true;

    }

    public  boolean saveds(BookOrdersDto bookload) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Book SET qty=(qty-?) WHERE book_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, bookload.getQty());
        pstm.setString(2,bookload.getBook_id());
        System.out.println(bookload.getBook_id());
        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean saveBook(BookDto dto) throws SQLException {

        return bookDAO.save(dto);

    }

    public boolean updateBook(final BookDto dto) throws SQLException {

        return bookDAO.update(dto);
    }

    public boolean deleteBook(String book_id) throws SQLException {
        return bookDAO.delete(book_id);
    }

    //meka damma impl ekata
    public List<BookDto> getAllBook() throws SQLException {
        return bookDAO.getAll();
    }

   /* public BookDto searchBook(String book_name) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "Select * from Book where book_name = ?";
        var pstm = connection.prepareStatement(sql);

        pstm.setString(1, book_name);

        ResultSet resultSet = pstm.executeQuery();
        BookDto dto= null;

        if (resultSet.next()){
            dto = new BookDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)

            );
        }
        return dto;
    }*/

    //meka damma impl ekata
    public BookDto SEarchBook(String book_id) throws SQLException {

        return bookDAO.search(book_id);
    }


   /* public List<String> getAllBooks() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Book";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ObservableList<String> custData = FXCollections.observableArrayList();
        try {

            ResultSet resultSet = pstm.executeQuery(sql);
            while (resultSet.next()) {
                custData.add(
                        resultSet.getString(1)
                );
            }
            return custData;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }*/
    public List<BookDto> SearchBookByAuthor(String author) throws SQLException {

        return bookDAO.SearchBookByAuthor(author);
    }

}

