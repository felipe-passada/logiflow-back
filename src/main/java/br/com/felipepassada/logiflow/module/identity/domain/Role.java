package br.com.felipepassada.logiflow.module.identity.domain;

public enum Role {
    ADMIN("Administrator"),
    CUSTOMER("Customer"),
    DRIVER("Driver");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
