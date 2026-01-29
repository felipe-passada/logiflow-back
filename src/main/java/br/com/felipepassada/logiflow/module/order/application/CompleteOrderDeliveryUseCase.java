package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.common.domain.exception.ResourceNotFoundException;
import br.com.felipepassada.logiflow.module.common.infra.security.SecurityUtils;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import br.com.felipepassada.logiflow.module.order.domain.exception.DriverNotApprovedException;
import br.com.felipepassada.logiflow.module.order.domain.exception.OrderStatusException;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;

import java.util.UUID;

public class CompleteOrderDeliveryUseCase {
    private OrderRepository orderRepository;
    private DriverRepository driverRepository;
    private SecurityUtils securityUtils;

    public CompleteOrderDeliveryUseCase(OrderRepository orderRepository, DriverRepository driverRepository, SecurityUtils securityUtils) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.securityUtils = securityUtils;
    }

    public void execute(UUID orderId) {
        UUID driverId = securityUtils.getCurrentUserId();
        var driver = driverRepository.findByUserId(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found for user ID: " + driverId));

        var orderJpa = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        if (!orderJpa.getDriverId().equals(driver.getId())) {
            throw new DriverNotApprovedException("Driver: " + driver.getId() + " is not authorized to complete this order: " + orderJpa.getId());
        }

        if(!orderJpa.getStatus().equals(OrderStatus.IN_TRANSIT)) {
            throw new OrderStatusException("Only orders that are in transit can be completed.");
        }

        orderJpa.setStatus(OrderStatus.DELIVERED);
        orderRepository.save(orderJpa);
    }
}

