package br.com.felipepassada.logiflow.module.order.domain.exception;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;

public class DriverNotApprovedException extends DomainException {
    public DriverNotApprovedException(String message) {
        super(message);
    }
}
