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
        long id = 1234;
        Address address = new Address("England", "London", "Sea street", 125);
        Client client = new Client("John", "Doe", id, address);
        Repository<Client> clientRepository = new Repository<>(clazz);

        clientRepository.add(client);
        assertEquals(client,clientRepository.find(c -> c.getId() == id));
        clientRepository.remove(client);
        assertNull(clientRepository.find(c -> c.getId() == id));
    }
}
