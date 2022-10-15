package managers;

import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import model.Client;
import model.Movie;
import model.sub.Genre;
import org.junit.Test;
import repositories.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieManagerTest {
    private final String title = "Star Wars I";
    private final Genre genre = Genre.SCI_FI;
    private final int ageRestriction = 13;
    private final int durationInMinutes = 160;
    private final int seatLimit = 140;


    @Test
    public void addMovieTest() throws MovieNotFoundException {
        Repository<Movie> repository = new Repository<>(Movie.class,"test");
        MovieManager movieManager = new MovieManager(repository);

        Movie movie = movieManager.addMovie(title,genre,ageRestriction,durationInMinutes,seatLimit);
        assertEquals(movie, movieManager.findMovie(1));
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(ageRestriction, movie.getAgeRestriction());
        assertEquals(durationInMinutes, movie.getDurationInMinutes());
        assertEquals(seatLimit, movie.getSeatLimit());
    }

    @Test
    public void removeMovieTest() throws MovieNotFoundException {
        Repository<Movie> repository = new Repository<>(Movie.class,"test");
        MovieManager movieManager = new MovieManager(repository);

        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        assertEquals(movie, movieManager.findMovie(1));
        assertThrows(MovieNotFoundException.class,
                () -> movieManager.removeMovie(2));
        assertTrue(movieManager.removeMovie(1));
    }

}
