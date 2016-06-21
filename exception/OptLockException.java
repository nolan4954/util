package com.best.web.htyt.util.exception;



public class OptLockException extends GLException {

    private static final long serialVersionUID = -4487429038874269500L;

    public static final String ERR_OPT_LOCK_CODE = "E010004000000013";

    public OptLockException(Exception e) {
        super(e);
    }

    public OptLockException(String msg) {
        super(msg);
    }

    public OptLockException() {
    }

    public OptLockException(Throwable e) {
        super(e);
    }

    public OptLockException(String msg, Throwable e) {
        super(msg, e);
    }

}
