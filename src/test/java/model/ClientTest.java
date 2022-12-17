package model;

import exceptions.WrongValueException;
import model.sub.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientTest {

    @Test
    public void clientCreationTest() {
        String name = "John";
        String surname = "Doe";
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        Address address = new Address(country, city, street, number);

        Client client = new Client(name,surname,address);
        assertEquals(name, client.getName());
        assertEquals(surname, client.getSurname());
        assertEquals(address, client.getAddress());
    }

    @Test
    public void clientSetNameTest()  {
        String name = "John";
        String surname = "Doe";
        String country = "England";
        String city = "London";
        String street = "Station Road";
        String error = "Name cannot be blank";
        int number = 12;
        Address address = new Address(country, city, street, number);

        Client client = new Client(name,surname,address);
        WrongValueException e = assertThrows(WrongValueException.class,
                () -> client.setName(null));
        assertEquals(error, e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> client.setName(""));
        assertEquals(error, e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> client.setName("   "));
        assertEquals(error, e.getMessage());

        try {
            client.setName("Mike");
            assertEquals("Mike",client.getName());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void clientSetSurnameTest() {
        String name = "John";
        String surname = "Doe";
        String country = "England";
        String city = "London";
        String street = "Station Road";
        int number = 12;
        String error = "Surname cannot be blank";
        Address address = new Address(country, city, street, number);

        Client client = new Client(name,surname,address);
        WrongValueException e = assertThrows(WrongValueException.class,
                () -> client.setSurname(null));
        assertEquals(error,e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> client.setSurname(""));
        assertEquals(error,e.getMessage());

        e = assertThrows(WrongValueException.class,
                () -> client.setSurname("   "));
        assertEquals(error,e.getMessage());

        try {
            client.setSurname("Smith");
            assertEquals("Smith",client.getSurname());
        } catch (WrongValueException ex) {
            throw new RuntimeException(ex);
        }
    }


}
