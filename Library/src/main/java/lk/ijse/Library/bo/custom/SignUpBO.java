package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.SignUpDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    public boolean checkUser(String password, String user) throws SQLException;
    public boolean saveSingUp(final SignUpDto dto) throws SQLException;

    }
