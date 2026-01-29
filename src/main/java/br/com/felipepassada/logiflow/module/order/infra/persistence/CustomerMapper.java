package br.com.felipepassada.logiflow.module.order.infra.persistence;

import br.com.felipepassada.logiflow.module.order.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerEntityJpa toJpa(Customer domain) {
        return new CustomerEntityJpa(
                domain.getId(),
                domain.getUserId(),
                domain.getFullName(),
                domain.getDocument(),
                domain.getPhone(),
                new AddressEmbeddable(
                        domain.getAddress().street(),
                        domain.getAddress().number(),
                        domain.getAddress().complement(),
                        domain.getAddress().city(),
                        domain.getAddress().state(),
                        domain.getAddress().cep(),
                        domain.getAddress().country()
                )
        );
    }

    public Customer toDomain(CustomerEntityJpa jpa) {
        return new Customer(
                jpa.getId(),
                jpa.getUserId(),
                jpa.getFullName(),
                jpa.getDocument(),
                jpa.getPhone(),
                jpa.getAddress().toDomain()
        );
    }
}
