package com.best.web.htyt.util.exception;


public class DAOException extends GLException {

    private static final long serialVersionUID = 880062235277333071L;

    private String[] values;

    public final static String ERR_DAO_EXCEPTION_DESC = "dao exception";

    public final static String ERR_DAO_EXCEPTION_CODE = "E030002000020000";

    public final static String ERR_DAO_REMOVE_FORBIDDEN_CODE = "error.dao.remove.forbidden";

    public final static String ERR_DAO_REMOVE_FORBIDDEN_DESC = "error.dao.remove.forbidden";

    // public final static String ERR_DAO_UPDATE_FORBIDDEN_CODE =
    // "error.dao.update.forbidden";
    //
    // public final static String ERR_DAO_UPDATE_FORBIDDEN_DESC =
    // "error.dao.update.forbidden";

    public DAOException(String msg, String[] values) {
        super(msg);
        this.values = values;

    }

    public String[] getVaules() {
        return values;
    }

    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String msg) {
        super(msg);
    }

    public DAOException(Throwable e) {
        super(e);
    }

    public DAOException( String msg,Throwable e) {
        super( msg,e);
    }

    public DAOException() {
    }
}
