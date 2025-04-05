package com.devsuperior.dsmeta.exceptions;

public class InvalidParameterException extends RuntimeException{
    public InvalidParameterException(){
        super("Invalid parameters provided");
    }
}
