package repositories;

import exceptions.WrongValueException;
import model.Client;
import model.sub.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RepositoryTest {
    @Test
    public void repositoryTest() throws WrongValueException {
        Address address = new Address("England", "London", "Sea street", 125);
        Client client = new Client("John", "Doe", address);
        Repository<Client> clientRepository = new Repository<>(Client.class,"test");

        clientRepository.add(client);
        assertEquals(client,clientRepository.find(1));
        clientRepository.remove(client);
        assertNull(clientRepository.find(1));
    }
}
