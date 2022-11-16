//package managers;
//
//import exceptions.ClientNotFoundException;
//import exceptions.MovieNotFoundException;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import model.Client;
//import model.Movie;
//import model.sub.Genre;
//import org.junit.Test;
//import repositories.AbstractRepository;
//import repositories.MovieRepository;
//import repositories.Repository;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class MovieManagerTest extends AbstractRepository {
//    private final String title = "Star Wars I";
//    private final Genre genre = Genre.SCI_FI;
//    private final int ageRestriction = 13;
//    private final int durationInMinutes = 160;
//    private final int seatLimit = 140;
//
//    @Test
//    public void addMovieTest() throws MovieNotFoundException {
//            MovieManager movieManager = new MovieManager(new MovieRepository());
//            movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
//            Movie movie = movieManager.findMovieByTitle("Die Hard").get(0);
//
//    }
//        Movie movie = movieManager.addMovie(title,genre,ageRestriction,durationInMinutes,seatLimit);
//        assertEquals(movie, movieManager.findMovie(1));
//        assertEquals(title, movie.getTitle());
//        assertEquals(genre, movie.getGenre());
//        assertEquals(ageRestriction, movie.getAgeRestriction());
//        assertEquals(durationInMinutes, movie.getDurationInMinutes());
//        assertEquals(seatLimit, movie.getSeatLimit());
//    }
////
////    @Test
////    public void removeMovieTest() throws MovieNotFoundException {
////        EntityManager em = factory.createEntityManager();
////        Repository<Movie> repository = new Repository<>(Movie.class, em);
////        MovieManager movieManager = new MovieManager(repository);
////
////        Movie movie = movieManager.addMovie(title, genre, ageRestriction, durationInMinutes, seatLimit);
////        assertEquals(movie, movieManager.findMovie(1));
////        assertThrows(MovieNotFoundException.class,
////                () -> movieManager.removeMovie(2));
////        assertTrue(movieManager.removeMovie(1));
////    }
////
////}
