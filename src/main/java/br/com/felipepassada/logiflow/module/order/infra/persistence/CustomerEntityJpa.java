package br.com.felipepassada.logiflow.module.order.infra.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "customer_profiles")
public class CustomerEntityJpa {
    @Id
    private UUID id;

    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    private String fullName;
    private String document;
    private String phone;

    @Embedded // O JPA mapeia os campos do Address na mesma tabela
    private AddressEmbeddable address;

    public CustomerEntityJpa() {
    }

    public CustomerEntityJpa(UUID id, UUID userId, String fullName, String document, String phone, AddressEmbeddable address) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.document = document;
        this.phone = phone;
        this.address = address;
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }
}