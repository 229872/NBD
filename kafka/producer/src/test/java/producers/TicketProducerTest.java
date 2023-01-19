package producers;

import exceptions.TicketNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import managers.ClientManager;
import managers.MovieManager;
import managers.TicketManager;
import model.Client;
import model.Movie;
import model.Ticket;
import org.junit.Test;
import repositories.ClientRepository;
import repositories.MovieRepository;
import repositories.TicketRepository;

import static org.junit.Assert.assertEquals;

public class TicketProducerTest {
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

    @Test
    public void produceTicketTest() throws WrongValueException, WrongTicketException, TicketNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        ClientManager clientManager = new ClientManager(new ClientRepository());
        TicketManager ticketManager = new TicketManager(new TicketRepository());

        Movie movie = movieManager.addMovie("Producer", "Producer", ageRestriction, durationInMinutes, seatLimit);
        Client client = clientManager.addClient("Producer", "Producer", "Producer", "Producer", "Producer", 15);
        Ticket added = ticketManager.addSeniorTicket(basePrice, seat, client, movie, 123, 65);
    }
}
