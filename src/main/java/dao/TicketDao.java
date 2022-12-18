package dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.QueryProvider;
import com.datastax.oss.driver.api.mapper.annotations.StatementAttributes;
import dao.providers.TicketQueryProvider;
import model.Ticket;

import java.util.List;
import java.util.UUID;

@Dao
public interface TicketDao {

    @StatementAttributes(consistencyLevel = "QUORUM", pageSize = 100)
    @QueryProvider(providerClass = TicketQueryProvider.class)
    void create(Ticket ticket);

    @QueryProvider(providerClass = TicketQueryProvider.class)
    void remove(Ticket ticket);

    @QueryProvider(providerClass = TicketQueryProvider.class)
    void update(Ticket ticket, UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = TicketQueryProvider.class)
    Ticket find(UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = TicketQueryProvider.class)
    List<Ticket> findAll();

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = TicketQueryProvider.class)
    List<Ticket> findAllByClient(UUID clientId);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = TicketQueryProvider.class)
    List<Ticket> findAllByMovie(UUID movieId);

}
