package br.com.felipepassada.logiflow.module.order.infra.persistence;

import br.com.felipepassada.logiflow.module.order.domain.valueObjects.Address;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEmbeddable {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String cep;
    private String country;

    public AddressEmbeddable() {
    }

    public AddressEmbeddable(String street, String number, String complement, String city, String state, String cep, String country) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.cep = cep;
        this.country = country;
    }

    // Getters e Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address toDomain() {
        return new Address(street, number, complement, city, state, cep, country);
    }
}
