package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.SupplierDAO;
import lk.ijse.Library.dto.SupplierDto;
import lk.ijse.Library.dto.tm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {



    @Override
    public List<SupplierDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier");
        List<SupplierDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String cus_name = resultSet.getString(2);
            String cus_address = resultSet.getString(3);
            int cus_tel = Integer.parseInt(resultSet.getString(4));

            var dto = new SupplierDto(cus_id, cus_name, cus_address, cus_tel);
            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public boolean save(SupplierDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES(?, ?, ?, ?)",dto.getS_id(),dto.getName(),dto.getAddress(),dto.getTel());
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException {
        return SQLUtil.execute( "UPDATE Supplier SET s_name = ?, address = ?, tel = ? WHERE S_id = ?",dto.getName(),dto.getAddress(),dto.getTel(),dto.getS_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE S_id = ?",id);
    }

    @Override
    public SupplierDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("Select * from Supplier where s_id = ?",id);
        SupplierDto dto= null;

        if (resultSet.next()){
            dto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            );
        }
        return dto;    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
