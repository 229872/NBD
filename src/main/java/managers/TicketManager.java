package managers;

import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import model.*;
import model.sub.SchoolType;
import repositories.TicketRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TicketManager {
    private TicketRepository repository;


    public TicketManager(TicketRepository repository) {
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Ticket addNormalTicket(double basePrice, int seat, Client client,
                                  Movie movie) throws WrongTicketException {
        boolean isTaken = repository.isSeatTaken(seat, movie.getId());
        if(!isTaken) {
            Ticket ticket = new Normal(basePrice, seat,  client.getId(), movie.getId());
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addStudentTicket(double basePrice, int seat, Client client,
                                   Movie movie, long studentIDCard, SchoolType schoolType) throws WrongTicketException {

        boolean isTaken = repository.isSeatTaken(seat, movie.getId());
        if(!isTaken) {
            Ticket ticket = new Student(basePrice, seat, client.getId(), movie.getId(), studentIDCard, schoolType);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addSeniorTicket(double basePrice, int seat, Client client,
                                  Movie movie, long seniorIDCard, int age) throws WrongTicketException {
        boolean isTaken = repository.isSeatTaken(seat, movie.getId());
        if(!isTaken) {
            Ticket ticket = new Senior(basePrice,seat,client.getId(),movie.getId(),seniorIDCard,age);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }
    }

    public Ticket findTicket(String uuid) throws TicketNotFoundException {
        try {
            return repository.find(UUID.fromString(uuid));
        } catch (NullPointerException e) {
            throw new TicketNotFoundException();
        }
    }

    public List<Ticket> findAllTickets() {
        return repository.findAll();
    }

    public List<Ticket> findTicketsByClient(String uuid) {
        return repository.findAllByClient(UUID.fromString(uuid));
    }

    public List<Ticket> findTicketsByMovie(String uuid) {
        return repository.findAllByMovie(UUID.fromString(uuid));
    }

    public void updateTicket(String uuid, Ticket ticket) {
        Objects.requireNonNull(ticket);
        repository.update(UUID.fromString(uuid), ticket);
    }

    public void removeTicket(String uuid) throws TicketNotFoundException {
        try {
            Ticket ticketToRemove = repository.find(UUID.fromString(uuid));
            repository.remove(ticketToRemove);
        } catch (NullPointerException e) {
            throw new TicketNotFoundException();
        }
    }
}
