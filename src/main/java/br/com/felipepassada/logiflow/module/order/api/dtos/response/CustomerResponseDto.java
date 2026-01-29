package br.com.felipepassada.logiflow.module.order.api.dtos.response;

import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;

import java.util.UUID;

public record CustomerResponseDto(
        UUID id,
        UUID userId,
        String email,
        String fullName,
        String document,
        String phone,
        Boolean isActive,
        AddressDto address
) {}