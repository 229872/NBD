package managers;

import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import model.*;
import model.sub.SchoolType;
import repositories.Repository;


import java.util.List;

public class TicketManager {
    private Repository<Ticket> repository;

//    public TicketManager() {
//        this.repository = RepositoryFactory.createRepository(Ticket.class);
//    }

    public TicketManager(Repository<Ticket> repository) {
//        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Ticket addNormalTicket(double basePrice, int seat, Client client,
                                  Movie movie) throws WrongTicketException, WrongValueException {
        EntityManager em = repository.getEm();
        Query query = em.createQuery("FROM Ticket WHERE seat=:seat AND movie=:movie");
        query.setParameter("seat",seat);
        query.setParameter("movie",movie);
        List<Ticket> checker = repository.find(query);

        if(checker.isEmpty()) {
            Ticket ticket = new Normal(basePrice,seat,client,movie);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addStudentTicket(double basePrice, int seat, Client client,
                                   Movie movie, long studentIDCard, SchoolType schoolType) throws WrongTicketException, WrongValueException {
        EntityManager em = repository.getEm();
        Query query = em.createQuery("FROM Ticket WHERE seat=:seat AND movie=:movie");
        query.setParameter("seat",seat);
        query.setParameter("movie",movie);
        List<Ticket> checker = repository.find(query);

        if(checker.isEmpty()) {
            Ticket ticket = new Student(basePrice, seat, client, movie, studentIDCard, schoolType);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
        }

    }

    public Ticket addSeniorTicket(double basePrice, int seat, Client client,
                                  Movie movie, long seniorIDCard, int age) throws WrongTicketException, WrongValueException {
        EntityManager em = repository.getEm();
        Query query = em.createQuery("FROM Ticket WHERE seat=:seat AND movie=:movie");
        query.setParameter("seat",seat);
        query.setParameter("movie",movie);
        List<Ticket> checker = repository.find(query);

        if(checker.isEmpty()) {
            Ticket ticket = new Senior(basePrice,seat,client,movie,seniorIDCard,age);
            ticket.getClient().addTicket(ticket);
            repository.add(ticket);
            return ticket;
        } else {
            throw new WrongTicketException("Seat is already taken");
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
