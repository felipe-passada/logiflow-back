package br.com.felipepassada.logiflow.module.fleet.api.dtos.response;

import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;

import java.util.UUID;

public record DriverSummaryResponseDto(
        UUID id,
        String fullName,
        String vehiclePlate,
        DriverStatus status
) {
}
