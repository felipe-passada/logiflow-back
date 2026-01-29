package br.com.felipepassada.logiflow.module.fleet.api.dtos.request;

public record RegisterDriverRequestDto(
        String email,
        String password,
        String fullName,
        String vehiclePlate
) {
}
