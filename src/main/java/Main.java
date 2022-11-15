import managers.MovieManager;
import model.sub.Genre;
import repositories.MovieRepository;

public class Main {
    public static void main(String[] args) {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        movieManager.addMovie("SW", Genre.SCI_FI, 12, 160, 50);

    }
}
