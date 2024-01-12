package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.BookDAO;
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

public class BookDAOImpl implements BookDAO {



    @Override
    public List<BookDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Book");
        List<BookDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String cus_book_id = resultSet.getString(1);
            String cus_book_name = resultSet.getString(2);
            int qty= resultSet.getInt(3);
            String cus_author = resultSet.getString(4);
            String cus_catagory = resultSet.getString(5);
            String cus_languege = resultSet.getString(6);
            String cus_d_id = resultSet.getString(7);
            String cus_s_id = resultSet.getString(8);


            var dto = new BookDto(cus_book_id, cus_book_name,qty, cus_author, cus_catagory,cus_languege,cus_d_id,cus_s_id);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(BookDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Book VALUES(?, ?, ?, ?, ?, ?, ?,?)",dto.getId(),dto.getName(),dto.getQty(),dto.getAuthor(), dto.getCatagory(),dto.getLanguege(),dto.getD_id(),dto.getS_id());
    }

    @Override
    public boolean update(BookDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Book SET book_name = ?, qty = ?, author = ?, category = ?, language = ?, d_id = ?, s_id = ? WHERE book_id = ?",dto.getName(),dto.getQty(), dto.getAuthor(),dto.getCatagory(),dto.getCatagory(),dto.getLanguege(), dto.getD_id(),dto.getS_id(), dto.getId());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Book WHERE book_id = ?",id);
    }

    @Override
    public BookDto search(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute( "Select * from Book where book_id = ?",id);
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
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }

    @Override
    public List<BookDto> SearchBookByAuthor(String author) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("Select * from Book where author = ?",author);
        ArrayList<BookDto> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(
                    new BookDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)

                    )
            );
        }
        return list;
    }

    @Override
    public boolean saveds(BookOrdersDto bookload) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Book SET qty=(qty-?) WHERE book_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, bookload.getQty());
        pstm.setString(2,bookload.getBook_id());
        System.out.println(bookload.getBook_id());
        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    @Override
    public  boolean updateQty(List<BookOrdersDto> orderDetailDTOS) throws SQLException {
        for (BookOrdersDto bookload : orderDetailDTOS) {
            if (!saveds(bookload)) {
                return false;
            }
        }
        return true;

    }

}
