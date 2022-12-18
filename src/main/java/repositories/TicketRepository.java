package repositories;


import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.TicketDao;
import dao.mappers.TicketMapper;
import dao.mappers.TicketMapperBuilder;
import model.Ticket;

import java.util.List;
import java.util.UUID;

public class TicketRepository extends AbstractRepository<Ticket> {

    public TicketRepository() {
        createTable();
    }

    @Override
    public void createTable() {
        getSession().execute(SchemaBuilder.createTable(CqlIdentifier.fromCql("tickets"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("ticket_id"), DataTypes.UUID)
                .withClusteringColumn(CqlIdentifier.fromCql("discriminator"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("client_id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("movie_id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("base_price"), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql("seat"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("student_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("senior_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("age"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("school_type"), DataTypes.TEXT)
                .build().setKeyspace("cinema"));

        getSession().execute(SchemaBuilder.createTable(CqlIdentifier.fromCql("tickets_by_client"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("client_id"), DataTypes.UUID)
                .withClusteringColumn(CqlIdentifier.fromCql("ticket_id"), DataTypes.UUID)
                .withClusteringColumn(CqlIdentifier.fromCql("discriminator"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("movie_id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("base_price"), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql("seat"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("student_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("senior_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("age"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("school_type"), DataTypes.TEXT)
                .build().setKeyspace("cinema"));

        getSession().execute(SchemaBuilder.createTable(CqlIdentifier.fromCql("tickets_by_movie"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("movie_id"), DataTypes.UUID)
                .withClusteringColumn(CqlIdentifier.fromCql("ticket_id"), DataTypes.UUID)
                .withClusteringColumn(CqlIdentifier.fromCql("discriminator"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("client_id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("base_price"), DataTypes.DOUBLE)
                .withColumn(CqlIdentifier.fromCql("seat"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("student_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("senior_id"), DataTypes.BIGINT)
                .withColumn(CqlIdentifier.fromCql("age"), DataTypes.INT)
                .withColumn(CqlIdentifier.fromCql("school_type"), DataTypes.TEXT)
                .build().setKeyspace("cinema"));
    }

    @Override
    public void add(Ticket item) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        ticketDao.create(item);
    }

    @Override
    public void remove(Ticket item) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        ticketDao.remove(item);
    }

    @Override
    public void update(UUID id, Ticket item) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        ticketDao.update(item, id);
    }

    @Override
    public Ticket find(UUID id) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        return ticketDao.find(id);
    }

    @Override
    public List<Ticket> findAll() {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        return ticketDao.findAll();
    }

    public List<Ticket> findAllByClient(UUID id) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        return ticketDao.findAllByClient(id);
    }

    public List<Ticket> findAllByMovie(UUID id) {
        TicketMapper ticketMapper = new TicketMapperBuilder(getSession()).build();
        TicketDao ticketDao = ticketMapper.ticketDao();

        return ticketDao.findAllByMovie(id);
    }



    public boolean isSeatTaken(int seat, UUID movieId) {
        List<Ticket> allTickets = findAll();
        return allTickets
                .stream()
                .filter(t -> t.getSeat() == seat && t.getMovieId().equals(movieId))
                .count() == 1;
    }
}
