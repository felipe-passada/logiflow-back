package br.com.felipepassada.logiflow.module.fleet.domain.exception;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;

public class DriverStatusException extends DomainException {
    public DriverStatusException(String message) {
        super(message);
    }
}
