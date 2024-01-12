package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.IncomeBO;
import lk.ijse.Library.dao.DAOFactory;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.IncomeDAO;
import lk.ijse.Library.dao.custom.impl.IncomeDAOImpl;
import lk.ijse.Library.dto.IncomeDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeBOimpl implements IncomeBO {

    IncomeDAO incomeDAO= (IncomeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INCOME);

    public boolean saveIcome(final IncomeDto dto) throws SQLException {
        return incomeDAO.save(dto);

    }

    public boolean updateIncome(final IncomeDto dto) throws SQLException {
        return incomeDAO.update(dto);
    }

    public boolean deleteIncome(String ic_id) throws SQLException {

        return incomeDAO.delete(ic_id);
    }

    public List<IncomeDto> getAllIncome() throws SQLException {

        return incomeDAO.getAll();
    }
}
