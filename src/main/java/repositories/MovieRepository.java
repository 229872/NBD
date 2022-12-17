package repositories;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.MovieDao;
import dao.mappers.MovieMapper;
import dao.mappers.MovieMapperBuilder;
import model.Movie;
import java.util.List;
import java.util.UUID;


public class MovieRepository extends AbstractRepository<Movie> {

    public MovieRepository() {
        createTable();
    }

    @Override
    public void createTable() {
        getSession().execute(SchemaBuilder.createTable(CqlIdentifier.fromCql("movies"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("title"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("genre"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("age_restriction"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("duration_in_minutes"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("seat_limit"), DataTypes.INT)
                .build().setKeyspace("cinema"));
    }


    @Override
    public void add(Movie item) {
        MovieMapper movieMapper = new MovieMapperBuilder(getSession()).build();
        MovieDao movieDao = movieMapper.movieDao();
        movieDao.create(item);
    }

    @Override
    public void remove(Movie item) {
        MovieMapper movieMapper = new MovieMapperBuilder(getSession()).build();
        MovieDao movieDao = movieMapper.movieDao();
        movieDao.remove(item);
    }

    @Override
    public void update(UUID id, Movie item) {
        MovieMapper movieMapper = new MovieMapperBuilder(getSession()).build();
        MovieDao movieDao = movieMapper.movieDao();
        movieDao.update(item, id);
    }

    @Override
    public Movie find(UUID id) {
        MovieMapper movieMapper = new MovieMapperBuilder(getSession()).build();
        MovieDao movieDao = movieMapper.movieDao();

        return movieDao.find(id);
    }

    @Override
    public List<Movie> findAll() {
        MovieMapper movieMapper = new MovieMapperBuilder(getSession()).build();
        MovieDao movieDao = movieMapper.movieDao();

        return movieDao.findAll();
    }

}
