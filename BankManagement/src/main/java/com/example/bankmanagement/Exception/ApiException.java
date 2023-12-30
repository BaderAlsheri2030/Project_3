package com.example.bankmanagement.Exception;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
