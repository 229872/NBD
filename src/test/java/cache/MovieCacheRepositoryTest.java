package cache;

import managers.MovieManager;
import model.Movie;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import repositories.MovieCacheRepository;
import repositories.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieCacheRepositoryTest {
    private MovieCacheRepository cacheRepository =
            new MovieCacheRepository(new MovieRepository());
    private final String title = "CacheTest";
    private final String genre = "test";
    private final int ageRestriction = 10;
    private final int durationInMinutes = 100;
    private final int seatLimit = 20;


    @Test
    public void crudTests() {
        Movie movie = new Movie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        cacheRepository.add(movie);
        assertEquals(movie, cacheRepository.find(movie.getUuid()));

    }



}
