package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import dao.providers.ClientQueryProvider;
import model.Client;

import java.util.List;
import java.util.UUID;

@Dao
public interface ClientDao {

    @StatementAttributes(consistencyLevel = "QUORUM", pageSize = 100)
    @QueryProvider(providerClass = ClientQueryProvider.class, entityHelpers = {Client.class})
    void create(Client client);

    @Delete
    void remove(Client client);

    @Update
    void update(Client client, UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = ClientQueryProvider.class, entityHelpers = {Client.class})
    Client find(UUID id);

    @StatementAttributes(consistencyLevel = "QUORUM")
    @QueryProvider(providerClass = ClientQueryProvider.class, entityHelpers = {Client.class})
    List<Client> findAll();

}
