//package managers;
//
//import exceptions.ClientAlreadyExistsException;
//import exceptions.ClientNotFoundException;
//import exceptions.WrongValueException;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import model.Client;
//import org.junit.Test;
//import repositories.Repository;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class ClientManagerTest {
//    private final String name = "John";
//    private final String surname = "Doe";
//    private final String country = "England";
//    private final String city = "London";
//    private final String street = "Sea street";
//    private final int number = 20;
//    private final EntityManagerFactory factory =
//            Persistence.createEntityManagerFactory("test");
//
//    @Test
//    public void addClientTest() throws ClientAlreadyExistsException, WrongValueException, ClientNotFoundException {
//        EntityManager em = factory.createEntityManager();
//        Repository<Client> repository = new Repository<>(Client.class, em);
//        ClientManager clientManager = new ClientManager(repository);
//
//        Client client = clientManager.addClient(name, surname, country, city, street, number);
//        assertEquals(client,clientManager.find(1));
//        assertEquals(name, client.getName());
//        assertEquals(surname, client.getSurname());
//        assertEquals(1, client.getId());
//    }
//
//    @Test
//    public void removeClientTest() throws ClientAlreadyExistsException, WrongValueException, ClientNotFoundException {
//        EntityManager em = factory.createEntityManager();
//        Repository<Client> repository = new Repository<>(Client.class, em);
//        ClientManager clientManager = new ClientManager(repository);
//
//        Client client = clientManager.addClient(name, surname, country, city, street, number);
//        assertEquals(client,clientManager.find(1));
//        assertThrows(ClientNotFoundException.class,
//                () -> clientManager.removeClient(5));
//        assertTrue(clientManager.removeClient(1));
//    }
//}
