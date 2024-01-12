package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.MemberBO;
import lk.ijse.Library.dao.DAOFactory;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.MemberDAO;
import lk.ijse.Library.dao.custom.impl.MemberDAOImpl;
import lk.ijse.Library.dto.MemberDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    MemberDAO memberDAO= (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

   /* public static List<MemberDto> getAllCustomerId() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Member");
        List<MemberDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            String mem_id = resultSet.getString(1);
            String mem_name = resultSet.getString(2);
            String address = resultSet.getString(3);
            int tel = Integer.parseInt(resultSet.getString(4));
            String user_name = resultSet.getString(5);

            var dto = new MemberDto(mem_id, mem_name,address, tel,user_name);
            dtoList.add(dto);
        }
        return dtoList;
    }*/

    public  MemberDto SEarchMember(String mem_id) throws SQLException {

        return memberDAO.search(mem_id);
    }


    public boolean saveMember(final MemberDto dto) throws SQLException {

        return memberDAO.save(dto);
    }

    public boolean updateMember(final MemberDto dto) throws SQLException {


        return memberDAO.update(dto);
    }

    public boolean deleteMember(String mem_id) throws SQLException {

        return memberDAO.delete(mem_id);
    }

    public List<MemberDto> getAllMember() throws SQLException {

       return memberDAO.getAll();
    }


    public List<MemberDto> getAllMem() throws SQLException {

        return memberDAO.getAll();
     }
    }

