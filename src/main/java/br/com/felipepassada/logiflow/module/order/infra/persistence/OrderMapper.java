package br.com.felipepassada.logiflow.module.order.infra.persistence;

import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.response.OrderResponseDto;
import br.com.felipepassada.logiflow.module.order.domain.Order;
import br.com.felipepassada.logiflow.module.order.domain.valueObjects.Address;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public OrderEntityJpa toJpa(Order order) {
        OrderEntityJpa jpa = new OrderEntityJpa();
        jpa.setId(order.getId());
        jpa.setCustomerId(order.getCustomerId());
        jpa.setDescription(order.getDescription());
        jpa.setWeight(order.getWeight());
        jpa.setStatus(order.getStatus());
        jpa.setOriginAddress(toAddressEmbeddable(order.getOriginAddress()));
        jpa.setDestinationAddress(toAddressEmbeddable((order.getDestinationAddress())));
        jpa.setCreatedAt(LocalDateTime.now());
        return jpa;
    }

    public Order toDomain(OrderEntityJpa jpa) {
        return new Order(
                jpa.getId(),
                jpa.getCustomerId(),
                jpa.getDriverId(),
                jpa.getDescription(),
                jpa.getWeight(),
                toAddressDomain(jpa.getOriginAddress()),
                toAddressDomain(jpa.getDestinationAddress()),
                jpa.getStatus()
        );
    }

    public OrderResponseDto toResponseDto(OrderEntityJpa jpa) {
        return new OrderResponseDto(
                jpa.getId(),
                jpa.getCustomerId(),
                jpa.getDescription(),
                jpa.getWeight(),
                jpa.getStatus().name(),
                AddressDto.fromDomain(toAddressDomain(jpa.getOriginAddress())),
                AddressDto.fromDomain(toAddressDomain(jpa.getDestinationAddress()))
        );
    }

    public static Address toAddressDomain(AddressEmbeddable embeddable) {
        if (embeddable == null) {
            return null;
        }
        return new Address(
                embeddable.getStreet(),
                embeddable.getNumber(),
                embeddable.getComplement(),
                embeddable.getCity(),
                embeddable.getState(),
                embeddable.getCep(),
                embeddable.getCountry()
        );
    }

    public static Address toAddressDomain(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        return new Address(
                dto.street(),
                dto.number(),
                dto.complement(),
                dto.city(),
                dto.state(),
                dto.cep(),
                dto.country()
        );
    }

    public static AddressEmbeddable toAddressEmbeddable(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressEmbeddable(
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