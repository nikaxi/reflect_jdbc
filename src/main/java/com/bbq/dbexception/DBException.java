package com.bbq.dbexception;

public class DBException extends Exception {

    public DBException(){}
    public DBException(String msg){
        super(msg);
    }
}
