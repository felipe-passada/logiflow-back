package br.com.felipepassada.logiflow.module.identity.api.dtos.response;

import br.com.felipepassada.logiflow.module.identity.domain.Role;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        Role role,
        boolean isActive
) {}
