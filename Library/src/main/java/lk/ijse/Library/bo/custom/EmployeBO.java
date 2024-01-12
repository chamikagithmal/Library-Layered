package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.EmployeDto;
import lk.ijse.Library.dto.tm.EmployeTm;

import java.sql.SQLException;
import java.util.List;

public interface EmployeBO extends SuperBO {
   boolean saveEmploye(final EmployeDto dto) throws SQLException;

    boolean updateEmploye(final EmployeDto dto) throws SQLException;

   boolean deleteEmploye(String emp_id) throws SQLException;
   List<EmployeDto> getAll() throws SQLException;

   EmployeDto SEarchEmploye(String code) throws SQLException;
}
