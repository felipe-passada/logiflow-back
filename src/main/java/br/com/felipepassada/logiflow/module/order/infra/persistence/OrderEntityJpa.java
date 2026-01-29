package br.com.felipepassada.logiflow.module.order.infra.persistence;

import br.com.felipepassada.logiflow.module.order.domain.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntityJpa {

    @Id
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "driver_id") // Nullable, pois nasce sem motorista
    private UUID driverId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double weight;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "origin_street")),
            @AttributeOverride(name = "city", column = @Column(name = "origin_city")),
            @AttributeOverride(name = "state", column = @Column(name = "origin_state")),
            @AttributeOverride(name = "complement", column = @Column(name = "origin_complement")),
            @AttributeOverride(name = "cep", column = @Column(name = "origin_cep")),
            @AttributeOverride(name = "number", column = @Column(name = "origin_number")),
            @AttributeOverride(name = "country", column = @Column(name = "origin_country"))
    })
    private AddressEmbeddable originAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "destination_street")),
            @AttributeOverride(name = "city", column = @Column(name = "destination_city")),
            @AttributeOverride(name = "state", column = @Column(name = "destination_state")),
            @AttributeOverride(name = "complement", column = @Column(name = "destination_complement")),
            @AttributeOverride(name = "cep", column = @Column(name = "destination_cep")),
            @AttributeOverride(name = "number", column = @Column(name = "destination_number")),
            @AttributeOverride(name = "country", column = @Column(name = "destination_country"))
    })
    private AddressEmbeddable destinationAddress;

    @Enumerated(EnumType.STRING) // Importante para salvar o nome (ex: "WAITING_FOR_DRIVER")
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public OrderEntityJpa() {

    }

    public OrderEntityJpa(UUID id, UUID customerId, UUID driverId, String description, Double weight, AddressEmbeddable originAddress, AddressEmbeddable destinationAddress, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.driverId = driverId;
        this.description = description;
        this.weight = weight;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.status = status;
        this.createdAt = LocalDateTime.now();
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

    public AddressEmbeddable getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(AddressEmbeddable originAddress) {
        this.originAddress = originAddress;
    }

    public AddressEmbeddable getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(AddressEmbeddable destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
