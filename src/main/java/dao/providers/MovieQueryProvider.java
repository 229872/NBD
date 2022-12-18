package dao.providers;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class MovieQueryProvider {
    private final CqlSession session;

    public MovieQueryProvider(MapperContext ctx) {
        this.session = ctx.getSession();
    }

    public Movie find(UUID id) {
        Select selectMovie = QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("movies"))
                .all()
                .where(Relation.column("id")
                .isEqualTo(literal(id)));

        return convertToMovie(Objects.requireNonNull(session.execute(selectMovie.build()).one()));
    }

    public List<Movie> findAll() {
        List<Movie> result = new ArrayList<>();
        session.execute(QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("movies"))
                .all()
                .build())
                .forEach(row -> result.add(convertToMovie(row)));

        return result;
    }

    private Movie convertToMovie(Row movie) {
        return new Movie(
              movie.getUuid("id"),
              movie.getString("title"),
              movie.getString("genre"),
              movie.getInt("age_restriction"),
              movie.getInt("duration_in_minutes"),
              movie.getInt("seat_limit")
        );
    }

}
