package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import org.junit.Test;
import repositories.AbstractRepository;
import repositories.MovieRepository;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieManagerTest {
    private final String title = "Star Wars I";
    private final String genre = "SCI_FI";
    private final int ageRestriction = 13;
    private final int durationInMinutes = 160;
    private final int seatLimit = 140;

    @Test
    public void addMovieTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie movie = movieManager.findMovieByTitle(title).get(0);
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(ageRestriction, movie.getAgeRestriction());
        assertEquals(durationInMinutes, movie.getDurationInMinutes());
    }

    @Test
    public void removeMovieTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);

        movieManager.removeMovie(movie.getUuid().getUuid().toString());
        assertThrows(MovieNotFoundException.class,
                () -> movieManager.findMovie(movie.getUuid().getUuid().toString()));

    }

    @Test
    public void findMovieByUUIDTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        Movie added = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie movie = movieManager.findMovie(added.getUuid().getUuid().toString());
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(ageRestriction, movie.getAgeRestriction());
        assertEquals(durationInMinutes, movie.getDurationInMinutes());
    }

    @Test
    public void findMovieByTitleTest() throws MovieNotFoundException {
        MovieManager movieManager = new MovieManager(new MovieRepository());
        movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie movie = movieManager.findMovieByTitle(title).get(0);
        assertEquals(title, movie.getTitle());
        assertEquals(genre, movie.getGenre());
        assertEquals(ageRestriction, movie.getAgeRestriction());
        assertEquals(durationInMinutes, movie.getDurationInMinutes());
    }

    @Test
    public void updateMovieTest() throws MovieNotFoundException {

        MovieManager movieManager = new MovieManager(new MovieRepository());
        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        Movie updated = new Movie("updatedMovie", "SCI_FI", 18, 150, 30);

        movieManager.updateMovie(movie.getUuid().getUuid().toString(), updated);

        Movie updatedMovie = movieManager.findMovieByTitle("updatedMovie").get(0);
        assertEquals("updatedMovie", updatedMovie.getTitle());
        assertEquals("SCI_FI", updatedMovie.getGenre());
        assertEquals(18, updatedMovie.getAgeRestriction());
        assertEquals(150, updatedMovie.getDurationInMinutes());
        assertEquals(30, updatedMovie.getSeatLimit());

    }

}