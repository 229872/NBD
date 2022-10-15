package managers;

import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import model.*;
import model.sub.SchoolType;
import repositories.Repository;

import java.util.function.Predicate;

public class TicketManager {
    private Repository<Ticket> repository;

    public TicketManager() {
        this.repository = new Repository<>(Ticket.class);
    }

    public TicketManager(Repository<Ticket> repository) {
        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Ticket addNormalTicket(int id, double basePrice, int seat, Client client,
                                  Movie movie) throws WrongTicketException, WrongValueException {
        Ticket check = repository.find(id);
        if(check == null && seat < movie.getSeatLimit()) {
            Ticket ticket = new Normal(id,basePrice,seat,client,movie);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException();
        }
    }

    public Ticket addStudentTicket(int id, double basePrice, int seat, Client client,
                                   Movie movie, long studentIDCard, SchoolType schoolType) throws WrongTicketException, WrongValueException {
        Ticket check = repository.find(id);
        if(check == null && seat < movie.getSeatLimit()) {
            Ticket ticket = new Student(id, basePrice, seat, client, movie, studentIDCard, schoolType);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException();
        }
    }

    public Ticket addSeniorTicket(int id, double basePrice, int seat, Client client,
                                  Movie movie, long seniorIDCard, int age) throws WrongTicketException, WrongValueException {
        Ticket check = repository.find(id);
        if(check == null && seat < movie.getSeatLimit()) {
            Ticket ticket = new Senior(id,basePrice,seat,client,movie,seniorIDCard,age);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException();
        }
    }

    public boolean removeTicket(int id) throws TicketNotFoundException {
        Ticket ticketToRemove = repository.find(id);
        if(ticketToRemove != null) {
            return repository.remove(ticketToRemove);
        } else {
            throw new TicketNotFoundException();
        }
    }

    public Ticket findTicket(long id) throws TicketNotFoundException {
        Ticket check = repository.find(id);
        if(check != null) {
            return check;
        } else {
            throw new TicketNotFoundException();
        }
    }

}
