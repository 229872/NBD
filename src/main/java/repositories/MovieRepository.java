package repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import model.Movie;
import model.UniqueId;
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
        Bson filter = eq("uuid", item.getUuid());
        moviesCollection.findOneAndDelete(filter);
    }

    @Override
    public void update(Movie item) {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        Bson filter = eq("uuid", item.getUuid());
        Bson update = Updates.combine(
                Updates.set("age_restriction", item.getAgeRestriction()),
                Updates.set("duration_in_minutes", item.getDurationInMinutes()),
                Updates.set("movie_genre", item.getGenre()),
                Updates.set("movie_title", item.getTitle()),
                Updates.set("seat_limit", item.getSeatLimit()),
                Updates.set("seats_taken", item.getSeatsTaken())
        );
        moviesCollection.updateOne(filter, update);
    }

    @Override
    public Movie find(UniqueId uuid) {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        Bson filter = eq("uuid", uuid);
        return moviesCollection.find(filter).first();
    }

    public List<Movie> findByName(String title) {
        MongoCollection<Movie> mongoCollection = getDb().getCollection("movies", Movie.class);
        Bson filter = eq("movie_title", title);
        return mongoCollection.find(filter).into(new ArrayList<>());
    }

    @Override
    public List<Movie> findAll() {
        MongoCollection<Movie> moviesCollection = getDb().getCollection("movies", Movie.class);
        return moviesCollection.find().into(new ArrayList<>());
    }
}
