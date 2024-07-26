package com.msp.shofydrop.exception;

public class OptimisticLockingException  extends RuntimeException{
    public OptimisticLockingException(String message, Throwable cause){
        super(message, cause);
    }
}
