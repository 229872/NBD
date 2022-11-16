package managers;

import com.mongodb.client.MongoCollection;
import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import model.*;
import model.sub.SchoolType;
import org.bson.conversions.Bson;
import repositories.AbstractRepository;
import repositories.Repository;
import java.util.Objects;
import java.util.UUID;
import static com.mongodb.client.model.Filters.eq;

public class TicketManager extends AbstractRepository {
    private Repository<Ticket> repository;


    public TicketManager(Repository<Ticket> repository) {
//        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public boolean isSeatTaken(int seat, Movie movie) {
        MongoCollection<Ticket> ticketsCollection = getDb().getCollection("tickets", Ticket.class);
        Bson query1 = eq("seat", seat);
        // drugie query do dodania jakos
        Bson query2 = eq("movie", movie);

        return ticketsCollection.countDocuments(query1) == 1;

    }


    public Ticket addNormalTicket(double basePrice, int seat, Client client,
                                  Movie movie) throws WrongTicketException, WrongValueException {
        boolean isTaken = isSeatTaken(seat, movie);
        if(!isTaken) {
            Ticket ticket = new Normal(basePrice,seat,client,movie);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addStudentTicket(double basePrice, int seat, Client client,
                                   Movie movie, long studentIDCard, SchoolType schoolType) throws WrongValueException, WrongTicketException {

        boolean isTaken = isSeatTaken(seat, movie);
        if(!isTaken) {
            Ticket ticket = new Student(basePrice, seat, client, movie, studentIDCard, schoolType);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addSeniorTicket(double basePrice, int seat, Client client,
                                  Movie movie, long seniorIDCard, int age) throws WrongTicketException, WrongValueException {
        boolean isTaken = isSeatTaken(seat, movie);
        if(!isTaken) {
            Ticket ticket = new Senior(basePrice,seat,client,movie,seniorIDCard,age);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }
    }

    public Ticket findTicket(String uuid) throws TicketNotFoundException {
        Ticket found = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(found != null) {
            return found;
        } else {
            throw new TicketNotFoundException();
        }
    }

    public void updateTicket(String uuid, Ticket ticket) {
        Objects.requireNonNull(ticket);
        repository.update(new UniqueId(UUID.fromString(uuid)), ticket);
    }

    public void removeTicket(String uuid) throws TicketNotFoundException {
        Ticket found = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(found != null) {
            repository.remove(found);
        } else {
            throw new TicketNotFoundException();
        }
    }
}
