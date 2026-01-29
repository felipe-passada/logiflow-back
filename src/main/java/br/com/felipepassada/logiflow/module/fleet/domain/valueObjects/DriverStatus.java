package br.com.felipepassada.logiflow.module.fleet.domain.valueObjects;

public enum DriverStatus {
    PENDING_APPROVAL("Pendente de Aprovação"),
    APPROVED("Aprovado"),
    REJECTED("Rejeitado"),
    SUSPENDED("Suspenso");

    private final String description;

    DriverStatus(String description) {
        this.description = description;
    }
}
