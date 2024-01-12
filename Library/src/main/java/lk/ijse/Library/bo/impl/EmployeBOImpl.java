package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.EmployeBO;
import lk.ijse.Library.dao.DAOFactory;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.impl.EmployeDAOImpl;
import lk.ijse.Library.dto.EmployeDto;
import lk.ijse.Library.dto.tm.EmployeTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeBOImpl implements EmployeBO {

    EmployeDAOImpl employeDAO= (EmployeDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    public boolean saveEmploye(final EmployeDto dto) throws SQLException {

        return employeDAO.save(dto);

    }

    public boolean updateEmploye(final EmployeDto dto) throws SQLException {

        return employeDAO.update(dto);
    }

    public boolean deleteEmploye(String emp_id) throws SQLException {
        return employeDAO.delete(emp_id);
    }

    public List<EmployeDto> getAll() throws SQLException {

        return employeDAO.getAll();
    }

    public EmployeDto SEarchEmploye(String code) throws SQLException {

        return employeDAO.search(code);
    }
}
