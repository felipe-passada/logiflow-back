package br.com.felipepassada.logiflow.module.identity.api.dtos.request;

public record RegisterUserRequestDto(
        String email,
        String password,
        String role
) {
}
