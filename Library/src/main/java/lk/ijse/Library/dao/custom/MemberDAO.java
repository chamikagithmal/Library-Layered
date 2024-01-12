package lk.ijse.Library.dao.custom;

import lk.ijse.Library.dao.CrudDAO;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dto.MemberDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MemberDAO extends CrudDAO<MemberDto> {
public List<MemberDto> getAll() throws SQLException ;

}
