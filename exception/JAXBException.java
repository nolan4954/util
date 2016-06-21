package com.best.web.htyt.util.exception;

public class JAXBException extends ApplicationException {

    private static final long serialVersionUID = 1770655943049000063L;

    public JAXBException() {
        super();
    }

    public JAXBException(String msg, Throwable e) {
        super(msg, e);
    }

    public JAXBException(String msg) {
        super(msg);
    }

    public JAXBException(Throwable e) {
        super(e);
    }
}
