package model.sub;

import exceptions.WrongValueException;

public class Address {
    private String country;
    private String city;
    private String street;
    private int number;

    public Address(String country, String city, String street, int number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws WrongValueException {
        if(country != null && !country.isBlank()) {
            this.country = country;
        } else {
            throw new WrongValueException("Country cannot be blank");
        }

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws WrongValueException {
        if (city != null && !city.isBlank()) {
            this.city = city;
        } else {
            throw new WrongValueException("City cannot be blank");
        }

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) throws WrongValueException {
        if (street != null && !street.isBlank()) {
            this.street = street;
        } else {
            throw new WrongValueException("Street cannot be blank");
        }

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws WrongValueException {
        if(number >= 0) {
            this.number = number;
        } else {
            throw new WrongValueException("Number of address cannot be negative");
        }

    }
}
