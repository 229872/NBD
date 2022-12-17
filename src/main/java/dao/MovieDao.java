package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import dao.providers.MovieQueryProvider;
import model.Movie;

import java.util.List;
import java.util.UUID;

@Dao
public interface MovieDao {

    @StatementAttributes(consistencyLevel = "ONE", pageSize = 100)
    @Insert
    void create(Movie movie);

    @Delete
    void remove(Movie movie);

    @Update
    void update(Movie movie, UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = MovieQueryProvider.class, entityHelpers = {Movie.class})
    Movie find(UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = MovieQueryProvider.class, entityHelpers = {Movie.class})
    List<Movie> findAll();

}
