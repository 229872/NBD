package managers;

import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import model.Client;
import model.sub.Address;
import repositories.ClientRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ClientManager {
    private ClientRepository repository;

    public ClientManager(ClientRepository repository) {
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Client addClient(String name, String surname, String country,
                            String city, String street, int number)  {
            Address address = new Address(country, city, street, number);
            Client client = new Client(name,surname,address);
            repository.add(client);
            return client;
    }

    public void removeClient(String uuid) throws ClientNotFoundException {
        try {
            Client client = repository.find(UUID.fromString(uuid));
            repository.remove(client);
        } catch (NullPointerException e) {
            throw new ClientNotFoundException();
        }
    }

    public Client findClient(String uuid) throws ClientNotFoundException {
        try {
            return repository.find(UUID.fromString(uuid));
        } catch (NullPointerException e) {
            throw new ClientNotFoundException();
        }
    }

    public List<Client> findAllClients() {
        return repository.findAll();
    }


    public void updateClient(String uuid, Client client) {
        Objects.requireNonNull(client);
        repository.update(UUID.fromString(uuid) ,client);
    }
}
