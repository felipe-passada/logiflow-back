package br.com.felipepassada.logiflow.module.order.domain;

import br.com.felipepassada.logiflow.module.common.domain.exception.DomainException;
import br.com.felipepassada.logiflow.module.order.domain.exception.OrderStatusException;
import br.com.felipepassada.logiflow.module.order.domain.valueObjects.Address;

import java.util.UUID;

public class Order {
    private UUID id;
    private UUID customerId;
    private UUID driverId;
    private String description;
    private Double weight;
    private Address originAddress;
    private Address destinationAddress;
    private OrderStatus status;

    public Order(UUID id, UUID customerId, UUID driverId, String description, Double weight, Address originAddress, Address destinationAddress) {
        if (originAddress.equals(destinationAddress)) {
            throw new OrderStatusException("Origin and destination cannot be the same");
        }
        this.id = id;
        this.customerId = customerId;
        this.driverId = driverId;
        this.description = description;
        this.weight = weight;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.status = OrderStatus.WAITING_FOR_DRIVER;
    }

    public Order(UUID id, UUID customerId, UUID driverId, String description, Double weight, Address originAddress, Address destinationAddress, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.driverId = driverId;
        this.description = description;
        this.weight = weight;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.status = status;
    }

    public void acceptOrder(UUID driverId) {
        if (this.status != OrderStatus.WAITING_FOR_DRIVER) {
            throw new OrderStatusException("Order cannot be accepted in its current status: " + this.status);
        }
        this.driverId = driverId;
        this.status = OrderStatus.PICKING_UP;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Address getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(Address originAddress) {
        this.originAddress = originAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(Address destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
