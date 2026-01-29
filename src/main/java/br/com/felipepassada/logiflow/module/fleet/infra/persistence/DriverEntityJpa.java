package br.com.felipepassada.logiflow.module.fleet.infra.persistence;

import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "driver_profiles")
public class DriverEntityJpa {
    @Id
    private UUID id;

    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    private String fullName;
    private String vehiclePlate;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    private boolean available;

    public DriverEntityJpa() {
    }

    public DriverEntityJpa(UUID id, UUID userId, String fullName, String vehiclePlate, DriverStatus status, boolean available) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.vehiclePlate = vehiclePlate;
        this.status = status;
        this.available = available;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}