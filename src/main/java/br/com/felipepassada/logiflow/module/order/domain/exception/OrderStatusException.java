package br.com.felipepassada.logiflow.module.order.domain.exception;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;

public class OrderStatusException extends DomainException {
    public OrderStatusException(String message) {
        super(message);
    }
}
