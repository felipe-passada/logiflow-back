package br.com.felipepassada.logiflow.module.fleet.infra.persistence;

import br.com.felipepassada.logiflow.module.fleet.domain.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public DriverEntityJpa toJpa(Driver domain) {
        return new DriverEntityJpa(
                domain.getId(),
                domain.getUserId(),
                domain.getFullName(),
                domain.getVehiclePlate(),
                domain.getStatus(),
                domain.isAvailable()
        );
    }

    public Driver toDomain(DriverEntityJpa jpa) {
        return new Driver(
                jpa.getId(),
                jpa.getUserId(),
                jpa.getFullName(),
                jpa.getVehiclePlate(),
                jpa.getStatus(),
                jpa.isAvailable()
        );
    }
}
