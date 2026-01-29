package br.com.felipepassada.logiflow.module.order.api.dtos;

import br.com.felipepassada.logiflow.module.order.domain.valueObjects.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "Number is required")
        String number,

        String complement, // Opcional, sem anotação de obrigatoriedade

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "CEP is required")
        String cep,

        @NotBlank(message = "Country is required")
        String country
) {
    public static Address toDomain(AddressDto address) {
        return new Address(
                address.street(),
                address.number(),
                address.complement(),
                address.city(),
                address.state(),
                address.cep(),
                address.country()
        );
    }

    public static AddressDto fromDomain(Address address) {
        return new AddressDto(
                address.street(),
                address.number(),
                address.complement(),
                address.city(),
                address.state(),
                address.cep(),
                address.country()
        );
    }
}
