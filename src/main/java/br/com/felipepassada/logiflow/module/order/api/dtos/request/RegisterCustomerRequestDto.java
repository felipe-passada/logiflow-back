package br.com.felipepassada.logiflow.module.order.api.dtos.request;

import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;

public record RegisterCustomerRequestDto(
        String email,
        String password,
        String fullName,
        String document,
        String phone,
        AddressDto address
) {
}


