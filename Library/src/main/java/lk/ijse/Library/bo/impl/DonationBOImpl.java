package lk.ijse.Library.bo.impl;

import lk.ijse.Library.bo.custom.DonationBO;
import lk.ijse.Library.dao.DAOFactory;
import lk.ijse.Library.dao.SQLUtil;
import lk.ijse.Library.dao.custom.DonationDAO;
import lk.ijse.Library.dao.custom.impl.DonationDAOImpl;
import lk.ijse.Library.dto.DonationDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationBOImpl implements DonationBO {

    DonationDAO donationDAO= (DonationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DONATION);

    public boolean saveDonation(final DonationDto dto) throws SQLException {

        return donationDAO.save(dto);

    }

    public boolean updateDonation(final DonationDto dto) throws SQLException {

        return donationDAO.update(dto);
    }

    public boolean deleteDonation(String d_id) throws SQLException {

        return donationDAO.delete(d_id);
    }

    public List<DonationDto> getAll() throws SQLException {

       return donationDAO.getAll();
    }

    public DonationDto SEarchDonation(String code) throws SQLException {

        return donationDAO.search(code);
    }

}
