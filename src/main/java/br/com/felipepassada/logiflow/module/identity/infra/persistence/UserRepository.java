package br.com.felipepassada.logiflow.module.identity.infra.persistence;

import br.com.felipepassada.logiflow.module.identity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntityJpa, UUID> {
    boolean existsByEmail(String email);

    Optional<UserEntityJpa> findByEmail(String email);
}
