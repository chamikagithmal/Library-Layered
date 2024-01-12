package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.SignUpBO;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dto.SignUpDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUpBOImpl implements SignUpBO {

    public boolean checkUser(String password, String user) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT user_name FROM User where user_name=? &&password=?",user,password);
        if (resultSet.next()){
            return true;
        }else {
            return false;
        }

    }

    public boolean saveSingUp(final SignUpDto dto) throws SQLException {

        return SQLUtil.execute("INSERT INTO User VALUES(?, ?, ?, ?)",dto.getName(),dto.getEmail(),dto.getUser_name(),dto.getPassword());

    }
}
