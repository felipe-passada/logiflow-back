package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;
import br.com.felipepassada.logiflow.module.common.domain.exception.ResourceNotFoundException;
import br.com.felipepassada.logiflow.module.common.infra.security.SecurityUtils;
import br.com.felipepassada.logiflow.module.fleet.domain.exception.DriverStatusException;
import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import br.com.felipepassada.logiflow.module.order.domain.exception.DriverNotApprovedException;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StartOrderDeliveryUseCase {
    private OrderRepository orderRepository;
    private DriverRepository driverRepository;
    private SecurityUtils securityUtils;

    public StartOrderDeliveryUseCase(OrderRepository orderRepository, DriverRepository driverRepository, SecurityUtils securityUtils) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.securityUtils = securityUtils;
    }

    public void execute(UUID orderId) {
        UUID driverId = securityUtils.getCurrentUserId();
        var driver = driverRepository.findByUserId(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for user ID: " + driverId));

        if(!driver.getStatus().equals(DriverStatus.APPROVED)) {
            throw new DriverNotApprovedException("Only approved drivers can accept orders.");
        }

        var orderJpa = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        if (!orderJpa.getDriverId().equals(driver.getId())) {
            throw new DriverNotApprovedException("Driver: " + driver.getId() + " is not assigned to this order: " + orderJpa.getId());
        }

        orderJpa.setStatus(OrderStatus.IN_TRANSIT);
        orderRepository.save(orderJpa);
    }
}
