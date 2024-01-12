package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.DonationDAO;
import lk.ijse.Library.dto.DonationDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationDAOImpl implements DonationDAO {


    @Override
    public List<DonationDto> getAll() throws SQLException {
            ResultSet resultSet = SQLUtil.execute("select * from Donation");
            ArrayList<DonationDto> objects = new ArrayList<>();

            while (resultSet.next()){
                objects.add(
                        new DonationDto(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getInt(4),
                                resultSet.getInt(5)
                        )
                );
            }
            return objects;
    }

    @Override
    public boolean save(DonationDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Donation VALUES(?, ?, ?, ?,?)",dto.getD_id(),dto.getD_name(),dto.getAddress(),dto.getTel(),dto.getMonetary_amount());
    }

    @Override
    public boolean update(DonationDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Donation SET D_name = ?, Address = ?, Tel = ?,Monetary_amount= ? WHERE D_id = ?",dto.getD_name(),dto.getAddress(),dto.getTel(),dto.getMonetary_amount(),dto.getD_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM Donation WHERE d_id = ?",id);
    }

    @Override
    public DonationDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Donation WHERE d_id = ?",id);
        DonationDto dto = null;
        if(resultSet.next()) {
            dto = new DonationDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5)

            );
        }
        return dto;
    }


    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
