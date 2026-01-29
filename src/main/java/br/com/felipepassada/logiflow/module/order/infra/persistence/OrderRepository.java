package br.com.felipepassada.logiflow.module.order.infra.persistence;

import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntityJpa, UUID> {
    List<OrderEntityJpa> findByStatus(OrderStatus status);
}
