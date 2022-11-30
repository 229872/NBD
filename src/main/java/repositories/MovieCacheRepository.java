package repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Movie;
import model.UniqueId;
import repositories.cachemodel.MovieCache;

import java.util.List;

public class MovieCacheRepository extends RepositoryDecorator<Movie> {

    private MovieCache movieCache;

    public MovieCacheRepository(MovieRepository repository) {
        super(repository);
        movieCache = new MovieCache();
    }

    @Override
    public void add(Movie item) {
        if (MovieCache.isHealthy()) {
            movieCache.save(item);
        }
        redisRepository.add(item);

    }

    @Override
    public void remove(Movie item) {
        if (MovieCache.isHealthy()) {
            movieCache.delete(item);
        }
        redisRepository.remove(item);
    }

    @Override
    public void update(UniqueId uuid, Movie item) {
        if (MovieCache.isHealthy()) {
            movieCache.save(item);
        }
        redisRepository.update(uuid, item);
    }

    @Override
    public Movie find(UniqueId id) {
        if (MovieCache.isHealthy()) {
            try {
                return movieCache.get(id);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return redisRepository.find(id);
    }

    @Override
    public List<Movie> findAll() {
        return redisRepository.findAll();
    }

    //flush cashe
    public void removeAll() {
        movieCache.deleteAll();
    }

    public Movie getFromDataBase(UniqueId id) {
        return redisRepository.find(id);
    }

    public void removeFromDataBase(Movie item) {
        redisRepository.remove(item);
    }
}
