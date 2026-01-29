package br.com.felipepassada.logiflow.module.identity.domain;

import br.com.felipepassada.logiflow.module.identity.domain.valueObjects.Password;

import java.util.UUID;

public class User {
    private UUID id;
    private String email;
    private Password password; // Value Object para lógica de hash
    private Role role;
    private boolean isActive;

    public User(UUID id, String email, Password password, Role role, boolean isActive) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isActive() {
        return isActive;
    }
}
