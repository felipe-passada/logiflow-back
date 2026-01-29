package br.com.felipepassada.logiflow.module.order.api.dtos.request;

import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrderRequestDto(
        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Weight is required")
        @Positive(message = "Weight must be positive")
        Double weight,

        @Valid
        @NotNull(message = "Origin address is required")
        AddressDto origin,

        @Valid
        @NotNull(message = "Destination address is required")
        AddressDto destination
) {}
