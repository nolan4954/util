package com.best.web.htyt.util.exception;

import java.util.HashMap;
import java.util.Map;


public class GLException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    Map<String, String> errorMap = new HashMap<String, String>();

    public GLException(Exception e) {
        super(e);
    }

    public GLException(String msg) {
        super(msg);
    }

    public GLException() {
    }

    public GLException(Throwable e) {
        super(e);
    }

    public GLException(String msg, Throwable e) {
        super(msg, e);
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
    
}
