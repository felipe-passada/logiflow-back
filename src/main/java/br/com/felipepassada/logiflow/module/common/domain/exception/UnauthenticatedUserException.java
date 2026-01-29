package br.com.felipepassada.logiflow.module.common.domain.exception;

public class UnauthenticatedUserException extends RuntimeException {
    public UnauthenticatedUserException() {
        super("Authentication is required to perform this action.");
    }
}
