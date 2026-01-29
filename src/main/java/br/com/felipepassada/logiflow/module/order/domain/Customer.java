package br.com.felipepassada.logiflow.module.order.domain;

import br.com.felipepassada.logiflow.module.order.domain.valueObjects.Address;

import java.util.UUID;

public class Customer {
    private UUID id;
    private UUID userId; // Referência apenas ao ID do User
    private String fullName;
    private String document;
    private String phone;
    private Address address;

    public Customer() {
    }

    public Customer(UUID id, UUID userId, String fullName, String document, String phone, Address address) {
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

    public UUID getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
