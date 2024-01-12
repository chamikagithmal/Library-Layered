package lk.ijse.Library.dao;

import lk.ijse.Library.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        USER, MEMBER, INCOME, EMPLOYEE, SUPPLIER, DONATION, BOOK, BOOK_ORDER
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new SignUpDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case INCOME:
                return new IncomeDAOImpl();
            case EMPLOYEE:
                return new EmployeDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();

            case DONATION:
                return new DonationDAOImpl();

            case BOOK:
                return new BookDAOImpl();

            case BOOK_ORDER:
                return new BookOrderDAOImpl();

            default:
                return null;
        }
    }
}