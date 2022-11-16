package managers;

import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import model.Client;
import org.junit.Test;
import repositories.ClientRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientManagerTest {
    private final String name = "John";
    private final String surname = "Doe";
    private final String country = "England";
    private final String city = "London";
    private final String street = "Sea street";
    private final int number = 20;

    @Test
    public void addClientTest() throws WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager(new ClientRepository());
        clientManager.addClient(name, surname, country, city, street, number);
        Client client = clientManager.findClientBySurname(surname).get(0);
        assertEquals(name, client.getName());
        assertEquals(surname, client.getSurname());
        assertEquals(country, client.getAddress().getCountry());
        assertEquals(city, client.getAddress().getCity());
        assertEquals(street, client.getAddress().getStreet());
        assertEquals(number, client.getAddress().getNumber());
    }

    @Test
    public void removeClientTest() throws WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager(new ClientRepository());
        Client client  = clientManager.addClient(name, surname, country, city, street, number);

        clientManager.removeClient(client.getUuid().getUuid().toString());
        assertThrows(ClientNotFoundException.class,
                () -> clientManager.findClient(client.getUuid().getUuid().toString()));

    }

    @Test
    public void findClientByUUIDTest() throws WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager(new ClientRepository());
        Client added = clientManager.addClient(name, surname, country, city, street, number);
        Client client = clientManager.findClient(added.getUuid().getUuid().toString());
        assertEquals(name, client.getName());
        assertEquals(surname, client.getSurname());
        assertEquals(country, client.getAddress().getCountry());
        assertEquals(city, client.getAddress().getCity());
        assertEquals(street, client.getAddress().getStreet());
        assertEquals(number, client.getAddress().getNumber());
    }

    @Test
    public void findClientBySurnameTest() throws WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager(new ClientRepository());
        clientManager.addClient(name, surname, country, city, street, number);
        Client client = clientManager.findClientBySurname(surname).get(0);
        assertEquals(name, client.getName());
        assertEquals(surname, client.getSurname());
        assertEquals(country, client.getAddress().getCountry());
        assertEquals(city, client.getAddress().getCity());
        assertEquals(street, client.getAddress().getStreet());
        assertEquals(number, client.getAddress().getNumber());
    }

    @Test
    public void updateClient() {

    }

}
