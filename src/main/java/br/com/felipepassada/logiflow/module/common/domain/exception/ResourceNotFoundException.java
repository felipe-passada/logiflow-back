package br.com.felipepassada.logiflow.module.common.domain.exception;

import java.util.UUID;

public class ResourceNotFoundException extends DomainException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, UUID id) {
        super(String.format("%s with ID %s not found.", resourceName, id));
    }
}
