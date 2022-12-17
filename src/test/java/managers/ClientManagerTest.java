package managers;

import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import model.Client;
import model.sub.Address;
import org.junit.Test;
import repositories.ClientRepository;

import static org.junit.Assert.*;

public class ClientManagerTest {
    private final String name = "John";
    private final String surname = "Doe";
    private final String country = "England";
    private final String city = "London";
    private final String street = "Sea street";
    private final int number = 20;

    private final ClientManager clientManager = new ClientManager(new ClientRepository());

    @Test
    public void addClientTest() throws ClientNotFoundException {
        Client added = clientManager.addClient(name, surname, country, city, street, number);
        Client found = clientManager.findClient(added.getId().toString());
        assertEquals(added, found);
    }

    @Test
    public void removeClientTest() throws ClientNotFoundException {
        ClientManager clientManager = new ClientManager(new ClientRepository());
        Client client = clientManager.addClient(name, surname, country, city, street, number);

        clientManager.removeClient(client.getId().toString());
        assertThrows(ClientNotFoundException.class,
                () -> clientManager.findClient(client.getId().toString()));


    }

    @Test
    public void findClientByIdTest() throws ClientNotFoundException {
        Client added = clientManager.addClient(name, surname, country, city, street, number);
        Client found = clientManager.findClient(added.getId().toString());
        assertEquals(added, found);
    }

    @Test
    public void findAllClientsTest() {
        clientManager.addClient(name, surname, country, city, street, number);
        clientManager.addClient(name, surname, country, city, street, number);

        assertTrue(clientManager.findAllClients().size() >= 2);
    }


    @Test
    public void updateClientTest() throws ClientNotFoundException {
        Client added = clientManager.addClient(name, surname, country, city, street, number);
        Client updated = new Client("updated", "client", new Address(country, city, street, number));
        clientManager.updateClient(added.getId().toString(), updated);

        Client found = clientManager.findClient(added.getId().toString());
        assertEquals(updated, found);
    }

}
