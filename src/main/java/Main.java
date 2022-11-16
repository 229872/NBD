import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import managers.ClientManager;
import managers.MovieManager;
import managers.TicketManager;
import model.Client;
import model.Movie;
import model.UniqueId;
import model.sub.Genre;
import model.sub.SchoolType;
import repositories.ClientRepository;
import repositories.MovieRepository;
import repositories.TicketRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws MovieNotFoundException, WrongValueException, ClientNotFoundException, WrongTicketException {
          MovieManager movieManager = new MovieManager(new MovieRepository());
//          movieManager.addMovie("Die Hard", Genre.ACTION, 18, 200, 30);
//        movieManager.removeMovie("ae0d9046-b86c-4bf2-a700-6b6ed9767071");

//        movieManager.removeMovie(new UniqueId(UUID.fromString("9545fab1-69dc-498e-8f8a-5e35c05a6bb2")));
//        Movie movie = movieManager.findMovie(new UniqueId(UUID.fromString("9545fab1-69dc-498e-8f8a-5e35c05a6bb2")));
//        System.out.println(movie);


//        MovieManager movieManager = new MovieManager(new MovieRepository());
//        movieManager.removeMovie("ec428997-c823-4844-845b-5668708fa0bf");
//        Movie movie =  movieManager.findMovie("ec428997-c823-4844-845b-5668708fa0bf");
        ClientManager clientManager = new ClientManager(new ClientRepository());
        clientManager.removeClient("096b663c-bbac-4df3-b0e8-752ce44b70cb");
//        Client client = clientManager.findClient("3cd48921-4ec8-4321-abc1-9cb6ff3baee2");
//        clientManager.removeClient("3cd48921-4ec8-4321-abc1-9cb6ff3baee2");


       TicketManager ticketManager = new TicketManager(new TicketRepository());
//       ticketManager.addStudentTicket(15, 2, client, movie, 155, SchoolType.PRIMARY_SCHOOL);
//       ticketManager.addNormalTicket(20, 15, client, movie);
//       ticketManager.addSeniorTicket(25, 25, client, movie, 255, 65);
       System.out.println("test");
    }
}
