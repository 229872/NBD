import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.WrongValueException;
import managers.ClientManager;
import managers.MovieManager;
import model.Client;
import model.Movie;
import model.UniqueId;
import model.sub.Genre;
import repositories.ClientRepository;
import repositories.MovieRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws MovieNotFoundException, WrongValueException, ClientNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
//        movieManager.addMovie("Lord Of The Rings", Genre.FANTASY, 16, 260, 30);
        UUID uuid = UUID.fromString("9545fab1-69dc-498e-8f8a-5e35c05a6bb2");
        Movie movie = movieManager.findMovie(new UniqueId(uuid));
        System.out.println(movie);
        Movie movie2 = movieManager.findMovieByTitle("Lord Of The Rings");
        System.out.println(movie2);
        ClientManager clientManager = new ClientManager(new ClientRepository());
//        Client client = clientManager.addClient("Johnny", "Doe", "USA", "LA", "first", 33);
//        Client client = clientManager.find(new UniqueId(UUID.fromString("90a8918a-6efa-4c85-8336-ce913dcb5dac")));
//        System.out.println(client);

    }
}
