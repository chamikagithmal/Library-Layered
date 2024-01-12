package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.SupplierBO;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.SupplierDAO;
import lk.ijse.Library.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.Library.dto.EmployeDto;
import lk.ijse.Library.dto.SupplierDto;
import lk.ijse.Library.dto.tm.SupplierTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO=new SupplierDAOImpl();

    public  SupplierDto SearchSuppiler(String s_id) throws SQLException {

       return supplierDAO.search(s_id);
    }

    public boolean saveSupplier(final SupplierDto dto) throws SQLException {


        return supplierDAO.save(dto);

    }

    public boolean updateSupplier(final SupplierDto dto) throws SQLException {

        return supplierDAO.update(dto);
    }

    public boolean deleteSupplier(String S_id) throws SQLException {

        return supplierDAO.delete(S_id);
    }

    public List<SupplierDto> getAllSupplier() throws SQLException {

        return supplierDAO.getAll();
    }
}
