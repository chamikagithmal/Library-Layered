package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.MemberDAO;
import lk.ijse.Library.dto.MemberDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {


    @Override
    public List<MemberDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Member");

        List<MemberDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String mem_id = resultSet.getString(1);
            String mem_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tel = Integer.parseInt(resultSet.getString(4));
            String user_name = resultSet.getString(5);

            var dto = new MemberDto(mem_id, mem_name,address, tel,user_name);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(MemberDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Member VALUES(?, ?, ?, ?,?)",dto.getMem_id(),dto.getMem_name(),dto.getAddress(),dto.getTel(),dto.getTel(),dto.getUser_name());
    }

    @Override
    public boolean update(MemberDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Member SET mem_name = ?, address = ?, tel = ?,user_name=? WHERE mem_id = ?",dto.getMem_name(),dto.getAddress(),dto.getTel(),dto.getUser_name(),dto.getMem_id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Member WHERE mem_id = ?",id);
    }

    @Override
    public MemberDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("Select * from Member where mem_id = ?",id);
        MemberDto dto= null;

        if (resultSet.next()){
            dto = new MemberDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }

    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
