package br.com.felipepassada.logiflow.module.fleet.domain;

import br.com.felipepassada.logiflow.module.fleet.domain.exception.DriverStatusException;
import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;

import java.util.UUID;

public class Driver {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String vehiclePlate;
    private DriverStatus status;
    private boolean available;

    public Driver(UUID id, UUID userId, String fullName, String vehiclePlate) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.vehiclePlate = vehiclePlate;
        this.status = DriverStatus.PENDING_APPROVAL;
        this.available = false;
    }

    public Driver(UUID id, UUID userId, String fullName, String vehiclePlate, DriverStatus status, boolean available) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.vehiclePlate = vehiclePlate;
        this.status = status;
        this.available = available;
    }

    public void toggleAvailability(boolean targetAvailability) {
        if (this.status != DriverStatus.APPROVED) {
            throw new DriverStatusException("Driver " + this.id + " is not approved to collect/deliver orders.");
        }
        this.available = targetAvailability;
    }

    public void approve() {
        if (this.status == DriverStatus.APPROVED) {
            throw new DriverStatusException("Driver " + this.id + " is already approved.");
        }
        this.status = DriverStatus.APPROVED;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
