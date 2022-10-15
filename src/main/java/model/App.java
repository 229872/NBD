package model;

import exceptions.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import managers.ClientManager;
import managers.MovieManager;
import managers.TicketManager;
import model.sub.Genre;

public class App {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private static EntityManager em = factory.createEntityManager();

    public static void main(String[] args) throws MovieNotFoundException, ClientAlreadyExistsException, WrongValueException, ClientNotFoundException, WrongTicketException, TicketNotFoundException {
        System.out.println("test");
        MovieManager movieManager = new MovieManager();
        movieManager.addMovie("test", Genre.HORROR, 150, 200, 300);
        ClientManager clientManager = new ClientManager();
        clientManager.addClient("John","Doe","England","London","Sea",3);

        Client client = clientManager.find(1);
        Movie movie = movieManager.findMovie(1);

        TicketManager ticketManager = new TicketManager();
        ticketManager.addNormalTicket(30.0, 2, client, movie);


    }
}
