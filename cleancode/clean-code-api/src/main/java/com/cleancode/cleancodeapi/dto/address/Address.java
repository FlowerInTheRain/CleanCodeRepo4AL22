package com.cleancode.cleancodeapi.dto.address;


import com.cleancode.cleancodeapi.enums.address.CountryEnum;

public class Address {
    private Integer number;
    private String street;
    private String town;
    private String zipCode;
    private CountryEnum country;

    public Integer getNumber() {
        return number;
    }
    // test

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(CountryEnum country) {
        this.country = country;
    }
}
