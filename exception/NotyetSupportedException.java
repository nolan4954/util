package com.best.web.htyt.util.exception;




public class NotyetSupportedException extends GLException {

    private static final long serialVersionUID = -2015846534435514785L;

    public NotyetSupportedException(Exception e) {
        super(e);
    }

    public NotyetSupportedException(String msg) {
        super(msg);
    }

    public NotyetSupportedException() {
    }

    public NotyetSupportedException(Throwable e) {
        super(e);
    }

    public NotyetSupportedException(Throwable e, String msg) {
        super(msg, e);
    }

}
