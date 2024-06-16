package br.com.veggierecipes.exception;

public class EmailAlreadyRegisteredException extends Exception {

    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
