package repositories;

import exceptions.WrongValueException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Client;
import model.sub.Address;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RepositoryTest {
    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("test");
    @Test
    public void repositoryTest() throws WrongValueException {
        EntityManager em = factory.createEntityManager();
        Address address = new Address("England", "London", "Sea street", 125);
        Client client = new Client("John", "Doe", address);
        Repository<Client> clientRepository = new Repository<>(Client.class, em);

        clientRepository.add(client);
        assertEquals(client,clientRepository.find(1));
        clientRepository.remove(client);
        assertNull(clientRepository.find(1));
    }
}
