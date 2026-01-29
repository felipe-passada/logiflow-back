package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.order.api.dtos.response.OrderResponseDto;
import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPendingOrdersUseCase {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public ListPendingOrdersUseCase(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderResponseDto> execute() {
        return orderRepository.findByStatus(OrderStatus.WAITING_FOR_DRIVER)
                .stream()
                .map(orderMapper::toResponseDto)
                .toList();
    }
}
