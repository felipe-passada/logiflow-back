package br.com.felipepassada.logiflow.module.order.domain.valueObjects;

import br.com.felipepassada.logiflow.module.order.infra.persistence.AddressEmbeddable;

public record Address(
        String street,
        String number,
        String complement,
        String city,
        String state,
        String cep,
        String country
) {
}
