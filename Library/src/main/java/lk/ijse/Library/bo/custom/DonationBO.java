package lk.ijse.Library.bo.custom;

import lk.ijse.Library.bo.SuperBO;
import lk.ijse.Library.dto.DonationDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DonationBO extends SuperBO {

    boolean saveDonation(final DonationDto dto) throws SQLException;

    boolean updateDonation(final DonationDto dto) throws SQLException;

    boolean deleteDonation(String d_id) throws SQLException;

    List<DonationDto> getAll() throws SQLException;

    DonationDto SEarchDonation(String code) throws SQLException;
}
