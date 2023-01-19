package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.Movie;
import model.Ticket;
import model.UniqueId;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


public class TicketRepository extends AbstractRepository<Ticket> {
    @Override
    public void add(Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        ticketsCollection.insertOne(item);
    }

    @Override
    public void remove(Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson filter = eq("uuid", item.getUuid());
        ticketsCollection.deleteOne(filter);
    }

    @Override
    public void update(UniqueId uuid, Ticket item) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson filter = eq("uuid", uuid);
        Bson update = Updates.combine(
                Updates.set("base_price", item.getBasePrice()),
                Updates.set("movie", item.getMovie()),
                Updates.set("client", item.getClient()),
                Updates.set("seat", item.getSeat()),
                Updates.set("ticketPrice", item.getTicketPrice())
        );
        ticketsCollection.updateOne(filter, update);
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


    public boolean isSeatTaken(int seat, Movie movie) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);

        Bson filter = Filters.and(eq("seat", seat), eq("movie", movie));

        return ticketsCollection.countDocuments(filter) == 1;

    }





}
