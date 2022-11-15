package repositories;

import com.mongodb.client.MongoCollection;
import model.Movie;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class MovieRepository extends AbstractRepository implements Repository<Movie> {
    @Override
    public void add(Movie item) {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        moviesCollection.insertOne(item);
    }

    @Override
    public void remove(Movie item) {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        Bson filter = eq("_id", item.getUuid());
        moviesCollection.deleteOne(filter);
    }

    @Override
    public Movie find(UUID uuid) {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        Bson filter = eq("movie_id", uuid);
        return moviesCollection.find(filter).first();
    }

    @Override
    public List<Movie> findAll() {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        return moviesCollection.find().into(new ArrayList<>());
    }
}
