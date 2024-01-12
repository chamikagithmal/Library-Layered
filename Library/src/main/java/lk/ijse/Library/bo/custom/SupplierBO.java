package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.SupplierDto;
import lk.ijse.Library.dto.tm.SupplierTm;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    SupplierDto SearchSuppiler(String s_id) throws SQLException;

    boolean saveSupplier(final SupplierDto dto) throws SQLException;

    boolean updateSupplier(final SupplierDto dto) throws SQLException;

    boolean deleteSupplier(String S_id) throws SQLException;

    List<SupplierDto> getAllSupplier() throws SQLException;
}
