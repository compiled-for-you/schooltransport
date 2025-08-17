package com.vbcodes.schooltransport.exception.customexceptions;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException(String message){
        super(message);
    }
}
