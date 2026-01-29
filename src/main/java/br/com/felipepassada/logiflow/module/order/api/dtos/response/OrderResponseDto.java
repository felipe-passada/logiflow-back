package br.com.felipepassada.logiflow.module.order.api.dtos.response;

import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;

import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        UUID customerId,
        String description,
        Double weight,
        String status,
        AddressDto origin,
        AddressDto destination
) {}
