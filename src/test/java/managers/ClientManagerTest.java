package managers;

import exceptions.ClientAlreadyExistsException;
import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import model.Client;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClientManagerTest {
    private final String name = "John";
    private final String surname = "Doe";
    private final long id = 1234;
    private final String country = "England";
    private final String city = "London";
    private final String street = "Sea street";
    private final int number = 20;

    @Test
    public void addClientTest() throws ClientAlreadyExistsException, WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager();

        Client client = clientManager.addClient(name, surname, id, country, city, street, number);
        assertEquals(client,clientManager.find(c -> c.getId() == id));
        assertEquals(name, client.getName());
        assertEquals(surname, client.getSurname());
        assertEquals(id, client.getId());
        assertThrows(ClientAlreadyExistsException.class,
                () -> clientManager.addClient(name, surname, id, country, city, street, number));
    }

    @Test
    public void removeClientTest() throws ClientAlreadyExistsException, WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager();
        Client client = clientManager.addClient(name, surname, id, country, city, street, number);
        assertEquals(client,clientManager.find(c -> c.getId() == id));
        assertThrows(ClientNotFoundException.class,
                () -> clientManager.removeClient(5));
        assertTrue(clientManager.removeClient(id));
    }

    @Test
    public void findClientTest() throws ClientAlreadyExistsException, WrongValueException, ClientNotFoundException {
        ClientManager clientManager = new ClientManager();
        Client client = clientManager.addClient(name, surname, id, country, city, street, number);
        assertEquals(client, clientManager.find(c -> c.getId() == id));
        assertEquals(client, clientManager.find(c -> c.getName().equals(name)));
        assertEquals(client, clientManager.find(c -> c.getSurname().equals(surname)));
    }
}
