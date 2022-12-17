package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import repositories.AbstractRepository;
import repositories.MovieRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MovieManagerTest {

    private final String title = "Star Wars I";
    private final String genre = "SCI_FI";
    private final int ageRestriction = 13;
    private final int durationInMinutes = 160;
    private final int seatLimit = 140;
    private static MovieManager movieManager;

    @BeforeClass
    public static void init() {
       movieManager = new MovieManager(new MovieRepository());
    }

    @AfterClass
    public static void close() {
        AbstractRepository.getSession().close();
    }

    @Test
    public void addMovieTest() throws MovieNotFoundException {
        Movie added =  movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie found = movieManager.findMovie(added.getId().toString());
        assertEquals(added, found);
    }

    @Test
    public void removeMovieTest() throws MovieNotFoundException {
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);

        movieManager.removeMovie(movie.getId().toString());
        assertThrows(MovieNotFoundException.class,
                () -> movieManager.findMovie(movie.getId().toString()));

        assertThrows(MovieNotFoundException.class,
                () -> movieManager.removeMovie(movie.getId().toString()));

    }

    @Test
    public void findMovieByIdTest() throws MovieNotFoundException {
        Movie added = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie found = movieManager.findMovie(added.getId().toString());
        assertEquals(added, found);
    }

    @Test
    public void findAllMoviesTest() {
        movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);

        assertTrue(movieManager.findAllMovies().size() >= 2);
    }

    @Test
    public void updateMovieTest() throws MovieNotFoundException {

        Movie added = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie updated = new Movie("updatedMovie", "SCI_FI", 18, 150, 30);
        movieManager.updateMovie(added.getId().toString(), updated);

        Movie found = movieManager.findMovie(added.getId().toString());
        assertEquals(updated, found);

    }

}