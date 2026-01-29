package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.common.domain.exception.ResourceNotFoundException;
import br.com.felipepassada.logiflow.module.common.infra.security.SecurityUtils;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import br.com.felipepassada.logiflow.module.order.domain.Order;
import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import br.com.felipepassada.logiflow.module.order.domain.exception.DriverNotApprovedException;
import br.com.felipepassada.logiflow.module.order.domain.exception.OrderStatusException;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompleteOrderDeliveryUseCase {
    private OrderRepository orderRepository;
    private DriverRepository driverRepository;
    private SecurityUtils securityUtils;
    private OrderMapper orderMapper;

    public CompleteOrderDeliveryUseCase(OrderRepository orderRepository,
                                        DriverRepository driverRepository,
                                        SecurityUtils securityUtils,
                                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.driverRepository = driverRepository;
        this.securityUtils = securityUtils;
        this.orderMapper = orderMapper;
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

        Order order = orderMapper.toDomain(orderJpa);
        order.delivered();

        orderRepository.save(orderMapper.toJpa(order));
    }
}

