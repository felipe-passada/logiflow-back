package br.com.felipepassada.logiflow.module.order.api;

import br.com.felipepassada.logiflow.module.order.api.dtos.request.CreateOrderRequestDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.response.OrderResponseDto;
import br.com.felipepassada.logiflow.module.order.application.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ListPendingOrdersUseCase listPendingOrdersUseCase;
    private final AcceptOrderUseCase acceptOrderUseCase;
    private final StartOrderDeliveryUseCase startOrderDeliveryUseCase;
    private final CompleteOrderDeliveryUseCase completeOrderDeliveryUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, ListPendingOrdersUseCase listPendingOrdersUseCase, AcceptOrderUseCase acceptOrderUseCase, StartOrderDeliveryUseCase startOrderDeliveryUseCase, CompleteOrderDeliveryUseCase completeOrderDeliveryUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listPendingOrdersUseCase = listPendingOrdersUseCase;
        this.acceptOrderUseCase = acceptOrderUseCase;
        this.startOrderDeliveryUseCase = startOrderDeliveryUseCase;
        this.completeOrderDeliveryUseCase = completeOrderDeliveryUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto requestDto) {
        OrderResponseDto responseDto = createOrderUseCase.execute(requestDto);

        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> listPendingOrders() {
        List<OrderResponseDto> orders = listPendingOrdersUseCase.execute();
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/accept")
    public ResponseEntity<Void> acceptOrder(@PathVariable UUID id) {
        acceptOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<Void> startDelivery(@PathVariable UUID id) {
        startOrderDeliveryUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Void> completeDelivery(@PathVariable UUID id) {
        completeOrderDeliveryUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
