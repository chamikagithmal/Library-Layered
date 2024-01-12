package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberBO extends SuperBO {
    MemberDto SEarchMember(String mem_id) throws SQLException;

    boolean saveMember(final MemberDto dto) throws SQLException;

    boolean updateMember(final MemberDto dto) throws SQLException;

    boolean deleteMember(String mem_id) throws SQLException;

    List<MemberDto> getAllMember() throws SQLException;

    List<MemberDto> getAllMem() throws SQLException;
}
