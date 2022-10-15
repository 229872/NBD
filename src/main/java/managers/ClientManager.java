package managers;

import exceptions.ClientAlreadyExistsException;
import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import model.sub.Address;
import model.Client;
import repositories.Repository;

import java.util.function.Predicate;

public class ClientManager {
    private Repository<Client> repository;

    public ClientManager() {
        this.repository = new Repository<>(Client.class);
    }

    public ClientManager(Repository<Client> repository) {
        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Client addClient(String name, String surname, long id, String country,
                            String city, String street, int number) throws ClientAlreadyExistsException, WrongValueException {
        Client clientToCheck = repository.find(id);
        if(clientToCheck == null) {
            Address address = new Address(country, city, street, number);
            Client client = new Client(name,surname,id,address);
            repository.add(client);
            return client;
        } else {
            throw new ClientAlreadyExistsException();
        }
    }

    public boolean removeClient(long id) throws ClientNotFoundException {
        Client clientToRemove = repository.find(id);
        if(clientToRemove != null) {
            return repository.remove(clientToRemove);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client find(long id) throws ClientNotFoundException {
        Client client = repository.find(id);
        if(client != null) {
            return client;
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void load() {

    }

    public void save() {

    }
}
