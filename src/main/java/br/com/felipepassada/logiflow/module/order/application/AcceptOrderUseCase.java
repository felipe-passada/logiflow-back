package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;
import br.com.felipepassada.logiflow.module.common.domain.exception.ResourceNotFoundException;
import br.com.felipepassada.logiflow.module.common.infra.security.SecurityUtils;
import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import br.com.felipepassada.logiflow.module.order.domain.Order;
import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import br.com.felipepassada.logiflow.module.order.domain.exception.DriverNotApprovedException;
import br.com.felipepassada.logiflow.module.order.domain.exception.OrderStatusException;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AcceptOrderUseCase {
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final SecurityUtils securityUtils;
    private  final OrderMapper orderMapper;

    public AcceptOrderUseCase(OrderRepository orderRepository, DriverRepository driverRepository, SecurityUtils securityUtils, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.securityUtils = securityUtils;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public void execute(UUID orderId) throws DomainException {
        UUID userId = securityUtils.getCurrentUserId();

        var driver = driverRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for user ID: " + userId));

        if(!driver.getStatus().equals(DriverStatus.APPROVED)) {
            throw new DriverNotApprovedException("Only approved drivers can accept orders.");
        }

        var orderJpa = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        if(!orderJpa.getStatus().equals(OrderStatus.WAITING_FOR_DRIVER)) {
            throw new OrderStatusException("Order is no longer available.");
        }

        orderJpa.setDriverId(driver.getId());

        Order order = orderMapper.toDomain(orderJpa);
        order.acceptOrder(orderJpa.getDriverId());

        orderRepository.save(orderMapper.toJpa(order));
    }
}
