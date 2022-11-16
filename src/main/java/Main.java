import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import managers.MovieManager;
import repositories.MovieRepository;

public class Main {
    public static void main(String[] args) throws MovieNotFoundException, WrongValueException, ClientNotFoundException, WrongTicketException {
//          MovieManager movieManager = new MovieManager(new MovieRepository());
//          movieManager.addMovie("Die Hard", Genre.ACTION, 18, 200, 30);
//        movieManager.removeMovie(new UniqueId(UUID.fromString("9545fab1-69dc-498e-8f8a-5e35c05a6bb2")));
//        Movie movie = movieManager.findMovie(new UniqueId(UUID.fromString("9545fab1-69dc-498e-8f8a-5e35c05a6bb2")));
//        System.out.println(movie);

        MovieManager movieManager = new MovieManager(new MovieRepository());
//        movieManager.addMovie("Star wars", Genre.SCI_FI, 12, 160, 30);
        movieManager.updateMovie("5c3c7324-0eee-48d8-93b6-69b3cb22079a", "dsfad",
                "Fanstasy", 33, 44, 222);
//        Movie movie = movieManager.findMovie("5c3c7324-0eee-48d8-93b6-69b3cb22079a");
//        System.out.println(movie);


//        MovieManager movieManager = new MovieManager(new MovieRepository());
//        movieManager.removeMovie("ec428997-c823-4844-845b-5668708fa0bf");
//        Movie movie =  movieManager.findMovie("ec428997-c823-4844-845b-5668708fa0bf");
//        ClientManager clientManager = new ClientManager(new ClientRepository());
//        clientManager.removeClient("e7083223-5b7b-48aa-b9c6-74af2cf303e3");
//        Client client = clientManager.findClient("3cd48921-4ec8-4321-abc1-9cb6ff3baee2");
//        clientManager.removeClient("3cd48921-4ec8-4321-abc1-9cb6ff3baee2");


//       TicketManager ticketManager = new TicketManager(new TicketRepository());
//       ticketManager.addStudentTicket(15, 2, client, movie, 155, SchoolType.PRIMARY_SCHOOL);
//       ticketManager.addNormalTicket(20, 15, client, movie);
//       ticketManager.addSeniorTicket(25, 25, client, movie, 255, 65);

    }
}
