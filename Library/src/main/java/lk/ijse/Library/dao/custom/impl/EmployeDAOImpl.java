package lk.ijse.Library.dao.custom.impl;

import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.EmployeDAO;
import lk.ijse.Library.dto.EmployeDto;
import lk.ijse.Library.dto.tm.EmployeTm;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAOImpl implements EmployeDAO {


    @Override
    public List<EmployeDto> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employe");
        List<EmployeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String emp_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tel = Integer.parseInt(resultSet.getString(4));
            Date dob = Date.valueOf(resultSet.getString(5));
            String user_name = resultSet.getString(6);

            var dto = new EmployeDto(emp_id, emp_name, address, tel, dob ,user_name);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean save(EmployeDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO Employe VALUES(?, ?, ?, ?,?,?)",dto.getEmp_id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getDOB(),dto.getUser_name());
    }

    @Override
    public boolean update(EmployeDto dto) throws SQLException {
        return SQLUtil.execute("UPDATE Employe SET name = ?, address = ?, tel = ?,DOB=?,User_id=? WHERE Emp_id = ?",dto.getEmp_id(),dto.getName(),dto.getAddress(),dto.getTel(),dto.getDOB(),dto.getUser_name());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Employe WHERE emp_id = ?",id);
    }

    @Override
    public EmployeDto search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employe WHERE emp_id = ?",id);

        EmployeDto dto = null;

        if(resultSet.next()) {
            dto = new EmployeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5),
                    resultSet.getString(6)

            );
        }
        return dto;
    }


    @Override
    public String getLastId() throws SQLException {
        return null;
    }
}
