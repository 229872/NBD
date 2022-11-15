package repositories;

import com.mongodb.client.MongoCollection;
import model.Client;
import model.UniqueId;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class ClientRepository extends AbstractRepository implements Repository<Client> {
    @Override
    public void add(Client item) {
        MongoCollection<Client> clientsCollection = getDb().getCollection("clients", Client.class);
        clientsCollection.insertOne(item);
    }

    @Override
    public void remove(Client item) {
        MongoCollection<Client> clientsCollection = getDb().getCollection("clients", Client.class);
        Bson filter = eq("uuid", item.getUuid().getUuid());
        clientsCollection.deleteOne(filter);
    }

    @Override
    public Client find(UniqueId id) {
        MongoCollection<Client> clientsCollection = getDb().getCollection("clients", Client.class);
        Bson filter = eq("uuid", id.getUuid());
        return clientsCollection.find(filter).first();
    }

    @Override
    public List<Client> findAll() {
        MongoCollection<Client> clientsCollection = getDb().getCollection("clients", Client.class);
        return clientsCollection.find().into(new ArrayList<>());
    }
}
