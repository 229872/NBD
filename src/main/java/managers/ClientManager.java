package managers;

import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.WrongValueException;
import jakarta.persistence.Persistence;
import model.Movie;
import model.UniqueId;
import model.sub.Address;
import model.Client;
import repositories.ClientRepository;
import repositories.Repository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ClientManager {
    private ClientRepository repository;

//    public ClientManager() {
//        this.repository = new Repository<>(Client.class, Persistence.c);
//    }

    public ClientManager(ClientRepository repository) {
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

    public void removeClient(String uuid) throws ClientNotFoundException {
        Client client = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(client != null) {
            repository.remove(client);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public Client findClient(String uuid) throws ClientNotFoundException {
        Client client = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(client != null) {
            return client;
        } else {
            throw new ClientNotFoundException();
        }
    }


    public List<Client> findClientBySurname(String surname) throws ClientNotFoundException {
        List<Client> found = repository.findBySurname(surname);
        if(!found.isEmpty()) {
            return found;
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void updateClient(Client client) {
        Objects.requireNonNull(client);
        repository.update(client);
    }
}
