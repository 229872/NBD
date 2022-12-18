package dao.providers;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BatchStatement;
import com.datastax.oss.driver.api.core.cql.BatchType;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import model.Normal;
import model.Senior;
import model.Student;
import model.Ticket;
import model.sub.SchoolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class TicketQueryProvider {
    private final CqlSession session;

    public TicketQueryProvider(MapperContext ctx) {
        this.session = ctx.getSession();
    }

    public void create(Ticket ticket) {
        Insert insertIntoTickets = createInsertStatement(ticket, "tickets");
        Insert insertIntoTicketsByClient = createInsertStatement(ticket, "tickets_by_client");
        Insert insertIntoTicketsByMovie = createInsertStatement(ticket, "tickets_by_movie");

        session.execute(BatchStatement.builder(BatchType.LOGGED)
                .addStatement(insertIntoTickets.build())
                .addStatement(insertIntoTicketsByClient.build())
                .addStatement(insertIntoTicketsByMovie.build())
                .build());

    }

    public void update(Ticket ticket, UUID id) {
        Update updateTickets = createUpdateStatement(ticket, id, "tickets");
        Update updateTicketsByClient = createUpdateStatement(ticket, id, "tickets_by_client");
        Update updateTicketsByMovie = createUpdateStatement(ticket, id, "tickets_by_movie");

        session.execute(BatchStatement.builder(BatchType.LOGGED)
                .addStatement(updateTickets.build())
                .addStatement(updateTicketsByClient.build())
                .addStatement(updateTicketsByMovie.build())
                .build());
    }

    public void remove(Ticket ticket) {
        Delete deleteFromTickets = QueryBuilder.deleteFrom("tickets")
                .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(ticket.getTicketId())));
        Delete deleteFromTicketsByClient = QueryBuilder.deleteFrom("tickets_by_client")
                .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(ticket.getTicketId())))
                .where(Relation.column("client_id").isEqualTo(QueryBuilder.literal(ticket.getClientId())));
        Delete deleteFromTicketsByMovie = QueryBuilder.deleteFrom("tickets_by_movie")
                .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(ticket.getTicketId())))
                .where(Relation.column("movie_id").isEqualTo(QueryBuilder.literal(ticket.getMovieId())));

        session.execute(BatchStatement.builder(BatchType.LOGGED)
                .addStatement(deleteFromTickets.build())
                .addStatement(deleteFromTicketsByClient.build())
                .addStatement(deleteFromTicketsByMovie.build())
                .build());
    }


    public Ticket find(UUID id) {
        Select selectTicket = QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("tickets"))
                .all()
                .where(Relation.column("ticket_id").isEqualTo(literal(id)));

        Row row = session.execute(selectTicket.build()).one();
        String discriminator = row.getString("discriminator");

        return switch (discriminator) {
            case "normal" -> convertToNormalTicket(row);
            case "senior" -> convertToSeniorTicket(row);
            case "student" -> convertToStudentTicket(row);
            default -> throw new IllegalArgumentException();
        };
    }

    public List<Ticket> findAll() {
        List<Ticket> result = new ArrayList<>();
        session.execute(QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("tickets_by_client"))
                .all()
                .build())
                .forEach(row -> {
                    String discriminator = row.getString("discriminator");
                    switch (Objects.requireNonNull(discriminator)) {
                        case "normal" -> result.add(convertToNormalTicket(row));
                        case "senior" -> result.add(convertToSeniorTicket(row));
                        case "student" -> result.add(convertToStudentTicket(row));
                        default -> throw new IllegalArgumentException();
                    }
                });
        return result;
    }

    public List<Ticket> findAllByClient(UUID clientId) {
        List<Ticket> result = new ArrayList<>();
        session.execute(QueryBuilder
                        .selectFrom(CqlIdentifier.fromCql("tickets_by_client"))
                        .all()
                        .where(Relation.column("client_id").isEqualTo(literal(clientId)))
                        .build())
                .forEach(row -> {
                    String discriminator = row.getString("discriminator");
                    switch (Objects.requireNonNull(discriminator)) {
                        case "normal" -> result.add(convertToNormalTicket(row));
                        case "senior" -> result.add(convertToSeniorTicket(row));
                        case "student" -> result.add(convertToStudentTicket(row));
                        default -> throw new IllegalArgumentException();
                    }
                });
        return result;
    }

    public List<Ticket> findAllByMovie(UUID movieId) {
        List<Ticket> result = new ArrayList<>();
        session.execute(QueryBuilder
                        .selectFrom(CqlIdentifier.fromCql("tickets_by_movie"))
                        .all()
                        .where(Relation.column("movie_id").isEqualTo(literal(movieId)))
                        .build())
                .forEach(row -> {
                    String discriminator = row.getString("discriminator");
                    switch (Objects.requireNonNull(discriminator)) {
                        case "normal" -> result.add(convertToNormalTicket(row));
                        case "senior" -> result.add(convertToSeniorTicket(row));
                        case "student" -> result.add(convertToStudentTicket(row));
                        default -> throw new IllegalArgumentException();
                    }
                });
        return result;
    }

    private Insert createInsertStatement(Ticket ticket, String tableName) {
        switch (ticket.getDiscriminator()) {
            case "normal" -> {
                Normal normal = (Normal) ticket;
                return QueryBuilder.
                        insertInto(tableName)
                        .value("client_id", literal(normal.getClientId()))
                        .value("movie_id", literal(normal.getMovieId()))
                        .value("ticket_id", literal(normal.getTicketId()))
                        .value("base_price", literal(normal.getBasePrice()))
                        .value("seat", literal(normal.getSeat()))
                        .value("discriminator", literal(normal.getDiscriminator()));
            }
            case "senior" -> {
                Senior senior = (Senior) ticket;
                return QueryBuilder.
                        insertInto(tableName)
                        .value("client_id", literal(senior.getClientId()))
                        .value("movie_id", literal(senior.getMovieId()))
                        .value("ticket_id", literal(senior.getTicketId()))
                        .value("base_price", literal(senior.getBasePrice()))
                        .value("seat", literal(senior.getSeat()))
                        .value("discriminator", literal(senior.getDiscriminator()))
                        .value("senior_id", literal(senior.getSeniorIDCard()))
                        .value("age", literal(senior.getAge()));

            }
            case "student" -> {
                Student student = (Student) ticket;
                return QueryBuilder.
                        insertInto(tableName)
                        .value("client_id", literal(student.getClientId()))
                        .value("movie_id", literal(student.getMovieId()))
                        .value("ticket_id", literal(student.getTicketId()))
                        .value("base_price", literal(student.getBasePrice()))
                        .value("seat", literal(student.getSeat()))
                        .value("discriminator", literal(student.getDiscriminator()))
                        .value("student_id", literal(student.getStudentIDCard()))
                        .value("school_type", literal(switch(student.getSchoolType()) {
                            case PRIMARY_SCHOOL -> "Primary school";
                            case STUDIES -> "Studies";
                            case HIGH_SCHOOL -> "High school";
                        }));
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private Update createUpdateStatement(Ticket ticket, UUID id, String tableName) {
        Update query;
        switch (ticket.getDiscriminator()) {
            case "normal" -> {
                Normal normal = (Normal) ticket;
                query = QueryBuilder.
                        update(tableName)
                        .setColumn("base_price", literal(normal.getBasePrice()))
                        .setColumn("seat", literal(normal.getSeat()))
                        .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(id)))
                        .where(Relation.column("discriminator").isEqualTo(QueryBuilder.literal(ticket.getDiscriminator())));

            }
            case "senior" -> {
                Senior senior = (Senior) ticket;
                query =  QueryBuilder.
                         update(tableName)
                        .setColumn("base_price", literal(senior.getBasePrice()))
                        .setColumn("seat", literal(senior.getSeat()))
                        .setColumn("senior_id", literal(senior.getSeniorIDCard()))
                        .setColumn("age", literal(senior.getAge()))
                        .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(id)))
                        .where(Relation.column("discriminator").isEqualTo(QueryBuilder.literal(ticket.getDiscriminator())));

            }
            case "student" -> {
                Student student = (Student) ticket;
                query =  QueryBuilder.
                        update(tableName)
                        .setColumn("base_price", literal(student.getBasePrice()))
                        .setColumn("seat", literal(student.getSeat()))
                        .setColumn("student_id", literal(student.getStudentIDCard()))
                        .setColumn("school_type", literal(switch(student.getSchoolType()) {
                            case PRIMARY_SCHOOL -> "Primary school";
                            case STUDIES -> "Studies";
                            case HIGH_SCHOOL -> "High school";
                        }))
                        .where(Relation.column("ticket_id").isEqualTo(QueryBuilder.literal(id)))
                        .where(Relation.column("discriminator").isEqualTo(QueryBuilder.literal(ticket.getDiscriminator())));
            }
            default -> throw new IllegalArgumentException();
        }

        switch(tableName) {
            case "tickets" -> {
                return query;
            }
            case "tickets_by_client" -> {
                return query.where(Relation.column("client_id").isEqualTo(QueryBuilder.literal(ticket.getClientId())));
            }
            case "tickets_by_movie" -> {
                return query.where(Relation.column("movie_id").isEqualTo(QueryBuilder.literal(ticket.getMovieId())));
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private Student convertToStudentTicket(Row row) {
        return new Student(
                row.getUuid("ticket_id"),
                row.getLong("student_id"),
                switch(Objects.requireNonNull(row.getString("school_type"))) {
                    case "Primary school" -> SchoolType.PRIMARY_SCHOOL;
                    case "Studies" -> SchoolType.STUDIES;
                    case "High school" -> SchoolType.HIGH_SCHOOL;
                    default -> throw new IllegalArgumentException();
                },
                row.getDouble("base_price"),
                row.getUuid("client_id"),
                row.getUuid("movie_id"),
                "student",
                row.getInt("seat"));

    }

    private Senior convertToSeniorTicket(Row row) {
        return new Senior(
                row.getUuid("ticket_id"),
                row.getLong("senior_id"),
                row.getInt("age"),
                row.getDouble("base_price"),
                row.getUuid("client_id"),
                row.getUuid("movie_id"),
                "senior",
                row.getInt("seat"));

    }

    private Normal convertToNormalTicket(Row row) {
        return new Normal(
                row.getUuid("ticket_id"),
                row.getDouble("base_price"),
                row.getUuid("client_id"),
                row.getUuid("movie_id"),
                "normal",
                row.getInt("seat"));
    }

}
