package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.common.infra.security.SecurityUtils;
import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.request.CreateOrderRequestDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.response.OrderResponseDto;
import br.com.felipepassada.logiflow.module.order.domain.Order;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderEntityJpa;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateOrderUseCase {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public CreateOrderUseCase(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDto execute(CreateOrderRequestDto requestDto) {
        UUID currentUserId = SecurityUtils.getCurrentUserId();

        Order order = new Order(
                UUID.randomUUID(),
                currentUserId,
                null,
                requestDto.description(),
                requestDto.weight(),
                OrderMapper.toAddressDomain(requestDto.origin()),
                OrderMapper.toAddressDomain(requestDto.destination())
        );

        OrderEntityJpa saved = orderRepository.save(orderMapper.toJpa(order));
        return orderMapper.toResponseDto(saved);
    }

}
