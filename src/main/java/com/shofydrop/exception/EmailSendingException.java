package com.shofydrop.exception;

public class EmailSendingException extends RuntimeException{
    public EmailSendingException(String message, Throwable cause){
        super(message, cause);
    }
}
