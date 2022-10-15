package managers;

import exceptions.ClientNotFoundException;
import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import model.Client;
import model.Movie;
import model.Normal;
import model.Ticket;
import model.sub.Address;
import model.sub.Genre;
import model.sub.SchoolType;
import org.junit.Test;
import repositories.Repository;


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

    private final Movie movie = new Movie(title,genre,ageRestriction,durationInMinutes,seatLimit);
    private final Movie movie2 = new Movie(title,genre,ageRestriction,durationInMinutes,seatLimit);
    private final Address address = new Address(country, city, street, number);
    private final Client client = new Client(name, surname, address);

    public TicketManagerTest() throws WrongValueException {
    }

    @Test
    public void addNormalTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        Ticket normal = ticketManager.addNormalTicket(basePrice, seat, client, movie);

        assertEquals(normal, ticketManager.findTicket(1));
        assertEquals(1, normal.getId());
        assertEquals(basePrice, normal.getBasePrice(), 0.000001);
        assertEquals(seat, normal.getSeat());
        assertEquals(movie, normal.getMovie());
        assertEquals(client, normal.getClient());
    }

    @Test
    public void addNormalExceptionsTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addNormalTicket(basePrice, seat, client, movie);

        assertThrows(WrongTicketException.class,
                () -> ticketManager.addNormalTicket(basePrice,seat2,client,movie));
        assertThrows(WrongTicketException.class,
                () -> ticketManager.addNormalTicket(basePrice, seat, client, movie));
        Ticket normal1 = ticketManager.addNormalTicket(basePrice,seat,client,movie2);
        assertEquals(normal1, ticketManager.findTicket(2));
        Ticket normal2 = ticketManager.addNormalTicket(basePrice,seat2, client, movie);
        assertEquals(normal2, ticketManager.findTicket(3));
    }

    @Test
    public void addNormalTicketToClient() throws WrongTicketException, WrongValueException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addNormalTicket(basePrice,seat,client,movie);
        ticketManager.addNormalTicket(basePrice, seat, client, movie2);
        int size = client.getListOfTickets().size();
        assertEquals(2,size);
    }

    @Test
    public void addStudentTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        Ticket student = ticketManager.addStudentTicket(basePrice, seat, client, movie,
                1, SchoolType.HIGH_SCHOOL);

        assertEquals(student, ticketManager.findTicket(1));
        assertEquals(1, student.getId());
        assertEquals(basePrice, student.getBasePrice(), 0.000001);
        assertEquals(seat, student.getSeat());
        assertEquals(movie, student.getMovie());
        assertEquals(client, student.getClient());
    }

    @Test
    public void addStudentExceptionsTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addStudentTicket(basePrice, seat, client, movie,
                1, SchoolType.HIGH_SCHOOL);

        assertThrows(WrongTicketException.class,
                () -> ticketManager.addStudentTicket(basePrice,seat2,client,movie,
                        1,SchoolType.HIGH_SCHOOL));
        assertThrows(WrongTicketException.class,
                () -> ticketManager.addStudentTicket(basePrice, seat, client, movie,
                        2,SchoolType.STUDIES));
        Ticket student1 = ticketManager.addStudentTicket(basePrice,seat,client,movie2,
                2,SchoolType.STUDIES);
        assertEquals(student1, ticketManager.findTicket(2));
        Ticket student2 = ticketManager.addStudentTicket(basePrice,seat2, client, movie,
                3,SchoolType.PRIMARY_SCHOOL);
        assertEquals(student2, ticketManager.findTicket(3));
    }

    @Test
    public void addStudentTicketToClient() throws WrongTicketException, WrongValueException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addStudentTicket(basePrice,seat,client,movie,
                1,SchoolType.PRIMARY_SCHOOL);
        ticketManager.addStudentTicket(basePrice, seat, client, movie2,
                2,SchoolType.HIGH_SCHOOL);
        int size = client.getListOfTickets().size();
        assertEquals(2,size);
    }

    @Test
    public void addSeniorTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        Ticket senior = ticketManager.addSeniorTicket(basePrice, seat, client, movie,
                1, 70);

        assertEquals(senior, ticketManager.findTicket(1));
        assertEquals(1, senior.getId());
        assertEquals(basePrice, senior.getBasePrice(), 0.000001);
        assertEquals(seat, senior.getSeat());
        assertEquals(movie, senior.getMovie());
        assertEquals(client, senior.getClient());
    }

    @Test
    public void addSeniorExceptionsTest() throws WrongTicketException, WrongValueException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addSeniorTicket(basePrice, seat, client, movie,
                1, 70);

        assertThrows(WrongTicketException.class,
                () -> ticketManager.addSeniorTicket(basePrice,seat2,client,movie,
                        1,70));
        assertThrows(WrongTicketException.class,
                () -> ticketManager.addSeniorTicket(basePrice, seat, client, movie,
                        2,70));
        Ticket senior1 = ticketManager.addSeniorTicket(basePrice,seat,client,movie2,
                2,70);
        assertEquals(senior1, ticketManager.findTicket(2));
        Ticket senior2 = ticketManager.addSeniorTicket(basePrice,seat2, client, movie,
                3,70);
        assertEquals(senior2, ticketManager.findTicket(3));
    }

    @Test
    public void addSeniorTicketToClient() throws WrongTicketException, WrongValueException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        ticketManager.addSeniorTicket(basePrice,seat,client,movie,
                1,80);
        ticketManager.addSeniorTicket(basePrice, seat, client, movie2,
                2,80);
        int size = client.getListOfTickets().size();
        assertEquals(2,size);
    }

    @Test
    public void removeTicket() throws WrongTicketException, WrongValueException, ClientNotFoundException, TicketNotFoundException {
        Repository<Ticket> repository = new Repository<>(Ticket.class,"test");
        TicketManager ticketManager = new TicketManager(repository);
        Ticket ticket = ticketManager.addNormalTicket(basePrice, seat, client, movie);
        assertEquals(ticket, ticketManager.findTicket(1));
        assertThrows(TicketNotFoundException.class,
                () -> ticketManager.findTicket(2));
        assertTrue(ticketManager.removeTicket(1));
    }

}
