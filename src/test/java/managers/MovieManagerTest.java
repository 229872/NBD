package managers;

import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import model.Client;
import model.Movie;
import model.sub.Genre;
import org.junit.Test;

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
        MovieManager movieManager = new MovieManager();
        Movie movie = movieManager.addMovie(title,genre,ageRestriction,durationInMinutes,seatLimit);
        assertEquals(movie, movieManager.findMovie(m -> m.getTitle().equals(title)));
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(ageRestriction, movie.getAgeRestriction());
        assertEquals(durationInMinutes, movie.getDurationInMinutes());
        assertEquals(seatLimit, movie.getSeatLimit());
    }

    @Test
    public void removeMovieTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager();
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        assertEquals(movie, movieManager.findMovie(m -> m.getTitle().equals(title)));
        assertThrows(MovieNotFoundException.class,
                () -> movieManager.removeMovie(m -> m.getTitle().equals("Jumanji")));
        assertTrue(movieManager.removeMovie(m -> m.getTitle().equals(title)));
    }

    @Test
    public void findMovieTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager();
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        assertEquals(movie, movieManager.findMovie(m -> m.getTitle().equals(title)));
        assertEquals(movie, movieManager.findMovie(m -> m.getGenre().equals(Genre.SCI_FI)));
        assertEquals(movie, movieManager.findMovie(m -> m.getSeatLimit() == seatLimit));
        assertEquals(movie, movieManager.findMovie(m -> m.getAgeRestriction() == ageRestriction));
        assertEquals(movie, movieManager.findMovie(m -> m.getDurationInMinutes() == durationInMinutes));
    }

}
