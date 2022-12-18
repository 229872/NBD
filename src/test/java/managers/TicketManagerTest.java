package managers;

import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import model.Client;
import model.Movie;
import model.Student;
import model.Ticket;
import model.sub.SchoolType;
import org.junit.Test;
import repositories.ClientRepository;
import repositories.MovieRepository;
import repositories.TicketRepository;

import static org.junit.Assert.*;

public class TicketManagerTest {
    private final String name = "John";
    private final String surname = "Doe";
    private final String country = "England";
    private final String city = "London";
    private final String street = "Sea street";
    private final int number = 20;
    private final String title = "Star Wars I";
    private final String genre = "SCI_FI";
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

    private final TicketManager ticketManager = new TicketManager(new TicketRepository());
    private final ClientManager clientManager = new ClientManager(new ClientRepository());
    private final MovieManager movieManager = new MovieManager(new MovieRepository());

    @Test
    public void addSeniorTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addSeniorTicket(basePrice, seat, client, movie, 123, 65);

        Ticket found = ticketManager.findTicket(added.getTicketId().toString());
        assertEquals(added, found);
    }

    @Test
    public void addNormalTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addNormalTicket(basePrice, seat, client, movie);

        Ticket found = ticketManager.findTicket(added.getTicketId().toString());
        assertEquals(added, found);
    }

    @Test
    public void addStudentTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        Ticket found = ticketManager.findTicket(added.getTicketId().toString());
        assertEquals(added, found);
    }

    @Test
    public void removeTicketTest() throws TicketNotFoundException, WrongValueException, WrongTicketException {

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        ticketManager.removeTicket(added.getTicketId().toString());
        assertThrows(TicketNotFoundException.class,
                () -> ticketManager.findTicket(added.getTicketId().toString()));
    }

    @Test
    public void findTicketByUUIDTest() throws TicketNotFoundException, WrongValueException, WrongTicketException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        Ticket found = ticketManager.findTicket(added.getTicketId().toString());
        assertEquals(added, found);
    }

    @Test
    public void findAllTicketsTest() throws WrongTicketException, WrongValueException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);

        ticketManager.addStudentTicket(basePrice, 1, client, movie, 123, SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, 2, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        assertTrue(ticketManager.findAllTickets().size() >= 2);
    }

    @Test
    public void findTicketsByClientTest() throws WrongTicketException, WrongValueException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);

        ticketManager.addStudentTicket(basePrice, 1, client, movie, 123, SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, 2, client, movie, 123, SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, 3, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        assertTrue(ticketManager.findTicketsByClient(client.getId().toString()).size() == 3);
    }

    @Test
    public void findTicketsByMovieTest() throws WrongTicketException, WrongValueException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);

        ticketManager.addStudentTicket(basePrice, 1, client, movie, 123, SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, 2, client, movie, 123, SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, 3, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        assertTrue(ticketManager.findTicketsByMovie(movie.getId().toString()).size() == 3);
    }

    @Test
    public void updateTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient(name, surname, country, city, street, number);
        Ticket added = ticketManager.addStudentTicket(basePrice, seat, client, movie, 123, SchoolType.PRIMARY_SCHOOL);

        Ticket updated = new Student(10, 20, client.getId(), movie.getId(), 555, SchoolType.PRIMARY_SCHOOL);
        ticketManager.updateTicket(added.getTicketId().toString(), updated);

        Ticket found = ticketManager.findTicket(added.getTicketId().toString());
        assertEquals(updated, found);


    }
}
