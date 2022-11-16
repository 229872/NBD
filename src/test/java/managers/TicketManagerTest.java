package managers;

import exceptions.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import model.Client;
import model.Movie;
import model.Normal;
import model.Ticket;
import model.sub.Address;
import model.sub.Genre;
import model.sub.SchoolType;
import org.junit.Test;
import repositories.ClientRepository;
import repositories.MovieRepository;
import repositories.Repository;
import repositories.TicketRepository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketManagerTest {
    private final String name = "John";
    private final String surname = "Doe";
    private final String country = "England";
    private final String city = "London";
    private final String street = "Sea street";
    private final int number = 20;
    private final String title = "Star Wars I";
    private final Genre genre = Genre.SCI_FI;
    private final int ageRestriction = 13;
    private final int durationInMinutes = 160;
    private final int seatLimit = 140;
    private final double basePrice = 14.0;
    private final int seat = 1;
    private final int seat2 = 2;
    private final String name2 = "Paul";
    private final String surname2 = "Comp";
    private final String country2 = "France";
    private final String city2 = "Paris";
    private final String street2 = "Baguette street";
    private final int number2 = 40;
    @Test
    public void addSeniorTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addSeniorTicket(basePrice, seat, client, movie, 123, 65);

        Ticket ticket = ticketManager.findTicket(added.getUuid().getUuid().toString());
        assertEquals(basePrice, ticket.getBasePrice(), 0.1);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
    }

    @Test
    public void addNormalTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addNormalTicket(basePrice, seat, client, movie);

        Ticket ticket = ticketManager.findTicket(added.getUuid().getUuid().toString());
        assertEquals(basePrice, ticket.getBasePrice(), 0.1);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
    }

    @Test
    public void addStudentTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        Ticket ticket = ticketManager.findTicket(added.getUuid().getUuid().toString());
        assertEquals(basePrice, ticket.getBasePrice(), 0.1);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
    }

    @Test
    public void removeTicketTest() throws TicketNotFoundException, WrongValueException, WrongTicketException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        ticketManager.removeTicket(added.getUuid().getUuid().toString());
        assertThrows(TicketNotFoundException.class,
                () -> ticketManager.findTicket(added.getUuid().getUuid().toString()));
    }

    @Test
    public void findTicketByUUIDTest() throws TicketNotFoundException, WrongValueException, WrongTicketException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        Ticket ticket = ticketManager.findTicket(added.getUuid().getUuid().toString());
        assertEquals(basePrice, ticket.getBasePrice(), 0.1);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
    }
    @Test
    public void updateTicket() {

    }
}
