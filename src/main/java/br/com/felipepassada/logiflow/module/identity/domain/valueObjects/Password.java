package br.com.felipepassada.logiflow.module.identity.domain.valueObjects;

public class Password {
    private final String value;

    public Password(String encodedValue) {
        if (encodedValue == null || encodedValue.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.value = encodedValue;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "********"; // Never expose the actual password
    }
}
