package model;

import exceptions.WrongValueException;
import model.sub.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressTest {
    @Test
    public void addressCreationTest() {
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);
        assertEquals(country, address.getCountry());
        assertEquals(city, address.getCity());
        assertEquals(street, address.getStreet());
        assertEquals(number, address.getNumber());
    }

    @Test
    public void addressSetCountryTest() {
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);

        WrongValueException e = assertThrows(WrongValueException.class,
                () -> address.setCountry(null));
        assertEquals("Country cannot be blank",e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setCountry(""));
        assertEquals("Country cannot be blank", e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setCountry("    "));
        assertEquals("Country cannot be blank", e.getMessage());

        try {
            address.setCountry("Germany");
            assertEquals("Germany",address.getCountry());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void addressSetCityTest() {
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);

        WrongValueException e = assertThrows(WrongValueException.class,
                () -> address.setCity(null));
        assertEquals("City cannot be blank",e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setCity(""));
        assertEquals("City cannot be blank", e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setCity("    "));
        assertEquals("City cannot be blank", e.getMessage());

        try {
            address.setCity("Manchester");
            assertEquals("Manchester",address.getCity());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void addressSetStreetTest() {
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);

        WrongValueException e = assertThrows(WrongValueException.class,
                () -> address.setStreet(null));
        assertEquals("Street cannot be blank",e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setStreet(""));
        assertEquals("Street cannot be blank", e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> address.setStreet("    "));
        assertEquals("Street cannot be blank", e.getMessage());

        try {
            address.setStreet("Road");
            assertEquals("Road",address.getStreet());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void addressSetNumberTest() {
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);

        WrongValueException e = assertThrows(WrongValueException.class,
                () -> address.setNumber(-1));
        assertEquals("Number of address cannot be negative",e.getMessage());

        try {
            address.setNumber(0);
            assertEquals(0,address.getNumber());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }
}
