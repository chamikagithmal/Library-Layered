package lk.ijse.Library.bo;

import lk.ijse.Library.bo.impl.*;
import lk.ijse.Library.dao.custom.impl.BookDAOImpl;

import static lk.ijse.Library.dao.DAOFactory.DAOTypes.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        BOOK, BOOKORDER, DONATION, EMPLOYE, INCOME, MEMBER, SUPPLIER,SIGNUP

    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {

            case BOOK:
                return new BookBOImpl();

            case BOOKORDER:
                return new BookOrderBOImpl();
            case DONATION:
                return new DonationBOImpl();
            case EMPLOYE:
                return new EmployeBOImpl();
            case INCOME:
                return new IncomeBOimpl();

            case MEMBER:
                return new MemberBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SIGNUP:
                return new SignUpBOImpl();

            default:
                return null;
        }
    }
}
