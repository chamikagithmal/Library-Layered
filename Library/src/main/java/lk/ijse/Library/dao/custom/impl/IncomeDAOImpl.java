package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.IncomeDAO;
import lk.ijse.Library.dto.IncomeDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAOImpl implements IncomeDAO {

    @Override
    public List<IncomeDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Income");
        List<IncomeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String ic_id = resultSet.getString(1);
            String amount = resultSet.getString(2);
            Date date = Date.valueOf(resultSet.getString(3));
            String mem_id = resultSet.getString(4);

            var dto = new IncomeDto(ic_id, amount, date, mem_id);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(IncomeDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO Income VALUES(?, ?, ?, ?)",dto.getIc_id(),dto.getAmount(),dto.getDate(),dto.getMem_id());
    }

    @Override
    public boolean update(IncomeDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Income SET amount = ?, date = ?, mem_id = ? WHERE ic_id = ?",dto.getAmount(),dto.getDate(),dto.getMem_id(),dto.getIc_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Income WHERE ic_id = ?",id);
    }

    @Override
    public IncomeDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
