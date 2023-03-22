package br.com.carv.reactiveexample.exception;

public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException(String message) {
        super(message);
    }
}
