package repositories.cachemodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movie;
import model.UniqueId;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

public class MovieCache extends Cache {
    private ObjectMapper obj;
    private String prefix;

    public MovieCache() {
        super();
        obj = new ObjectMapper();
        prefix = "movie:";
    }

    public void save(Movie movie) {
        String movieString;
        try {
            movieString = obj.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String key = prefix + movie.getUuid().toString();

        //redis set
        pool.set(key, movieString);
    }

    public Movie get(UniqueId id) throws JsonProcessingException {
        String key = prefix + id.toString();
        String value = pool.get(key);
        return obj.readValue(value, Movie.class);
    }

    public void delete(Movie movie) {
        String key = prefix + movie.getUuid().toString();
        pool.del(key);
    }

    public void deleteAll() {
        JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().build();
        try (Jedis jedis = new Jedis(getHostAndPort(), clientConfig)) {
            //Czyscimy caly cache
            jedis.flushAll();
        }
    }
     public long size() {
        return pool.dbSize();
     }

}
