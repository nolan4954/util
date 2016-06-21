package com.best.web.htyt.util.exception;


public abstract class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String msg, Throwable e) {
        super(msg, e);
    }

    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(Throwable e) {
        super(e);
    }

}
