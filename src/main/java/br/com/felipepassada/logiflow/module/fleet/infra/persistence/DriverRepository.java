package br.com.felipepassada.logiflow.module.fleet.infra.persistence;

import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntityJpa, UUID> {

    Page<DriverEntityJpa> findAllByStatus(DriverStatus status, Pageable pageable);
    Optional<DriverEntityJpa> findByUserId(UUID userId);
}
