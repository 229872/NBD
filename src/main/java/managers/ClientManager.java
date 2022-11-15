package managers;

import exceptions.ClientNotFoundException;
import exceptions.WrongValueException;
import jakarta.persistence.Persistence;
import model.UniqueId;
import model.sub.Address;
import model.Client;
import repositories.Repository;

import java.util.UUID;


public class ClientManager {
    private Repository<Client> repository;

//    public ClientManager() {
//        this.repository = new Repository<>(Client.class, Persistence.c);
//    }

    public ClientManager(Repository<Client> repository) {
//        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Client addClient(String name, String surname, String country,
                            String city, String street, int number) throws WrongValueException {
            Address address = new Address(country, city, street, number);
            Client client = new Client(name,surname,address);
            repository.add(client);
            return client;
    }

    public void removeClient(UniqueId uuid) throws ClientNotFoundException {
        Client clientToRemove = repository.find(uuid);
        if(clientToRemove != null) {
            repository.remove(clientToRemove);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client find(UniqueId uuid) throws ClientNotFoundException {
        Client client = repository.find(uuid);
        if(client != null) {
            return client;
        } else {
            throw new ClientNotFoundException();
        }
    }
}
