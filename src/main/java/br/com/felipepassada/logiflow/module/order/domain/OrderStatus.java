package br.com.felipepassada.logiflow.module.order.domain;

public enum OrderStatus {
    WAITING_FOR_DRIVER("Waiting for Driver"),
    PICKING_UP("Picking Up"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered"),
    CANCELED("Canceled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
}
