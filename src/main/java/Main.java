import exceptions.MovieNotFoundException;
import managers.MovieManager;
import model.Movie;
import model.sub.Genre;
import repositories.MovieRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        Movie movie = movieManager.findMovie(UUID.randomUUID());
//        movieManager.addMovie("SW", Genre.SCI_FI, 12, 160, 50);

    }
}
