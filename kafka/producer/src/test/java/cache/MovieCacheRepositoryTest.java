package cache;

import model.Movie;
import org.junit.Test;
import repositories.MovieCacheRepository;
import repositories.MovieRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void clearCacheTest() {
        Movie movie = new Movie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        cacheRepository.add(movie);
        assertTrue(cacheRepository.size() > 0);
        cacheRepository.removeAll();
        assertTrue(cacheRepository.size() == 0);
    }

    @Test
    public void getDataWhenBrokenConnectionWithMongo() {
        Movie movie = new Movie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        cacheRepository.add(movie);
        cacheRepository.breakRedisConnection();
        assertEquals(movie, cacheRepository.find(movie.getUuid()));
    }




}
