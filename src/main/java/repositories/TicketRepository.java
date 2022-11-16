package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Ticket;
import model.UniqueId;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class TicketRepository extends AbstractRepository implements Repository<Ticket> {
    @Override
    public void add(Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        ticketsCollection.insertOne(item);
    }

    @Override
    public void remove(Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson filter = eq("uuid", item.getUuid().getUuid());
        ticketsCollection.deleteOne(filter);
    }

    @Override
    public void update(UniqueId uuid, Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson filter = eq("uuid", uuid);
//        Bson update = Updates.combine(
//                Updates.set("address", item.getAddress()),
//                Updates.set("client_name", item.getName()),
//                Updates.set("client_surname", item.getSurname())
//        );
//        ticketsCollection.updateOne(filter, update);
    }

    @Override
    public Ticket find(UniqueId id) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson filter = eq("uuid", id.getUuid());
        return ticketsCollection.find(filter).first();
    }

    @Override
    public List<Ticket> findAll() {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        return ticketsCollection.find().into(new ArrayList<>());
    }


}
