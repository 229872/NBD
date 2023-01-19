package cache;

import org.junit.Test;
import repositories.cachemodel.MovieCache;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieCacheTest {

    @Test
    public void movieCacheHealthTest() throws InterruptedException {
        MovieCache movieCache = new MovieCache();
        assertTrue(MovieCache.isHealthy());
    }
}
