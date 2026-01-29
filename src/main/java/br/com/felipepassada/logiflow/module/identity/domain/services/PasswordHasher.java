package br.com.felipepassada.logiflow.module.identity.domain.services;

public interface PasswordHasher {
    String hash(String rawPassword);
}