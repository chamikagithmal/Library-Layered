package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.IncomeDto;

import java.sql.SQLException;
import java.util.List;

public interface IncomeBO extends SuperBO {

    boolean saveIcome(final IncomeDto dto) throws SQLException;

    boolean updateIncome(final IncomeDto dto) throws SQLException;

    boolean deleteIncome(String ic_id) throws SQLException;

    List<IncomeDto> getAllIncome() throws SQLException;

}
