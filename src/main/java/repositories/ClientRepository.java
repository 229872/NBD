package repositories;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.ClientDao;
import dao.mappers.ClientMapper;
import dao.mappers.ClientMapperBuilder;
import model.Client;

import java.util.List;
import java.util.UUID;

public class ClientRepository extends AbstractRepository<Client>  {

    public ClientRepository() {
        createTable();
    }

    @Override
    public void createTable() {
        getSession().execute(SchemaBuilder.createTable(CqlIdentifier.fromCql("clients"))
                .ifNotExists()
                .withPartitionKey(CqlIdentifier.fromCql("id"), DataTypes.UUID)
                .withColumn(CqlIdentifier.fromCql("name"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("surname"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("city"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("country"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("street"), DataTypes.TEXT)
                .withColumn(CqlIdentifier.fromCql("number"), DataTypes.INT)
                .build().setKeyspace("cinema"));
    }

    @Override
    public void add(Client item) {
        ClientMapper clientMapper = new ClientMapperBuilder(getSession()).build();
        ClientDao clientDao = clientMapper.clientDao();
        clientDao.create(item);
    }

    @Override
    public void remove(Client item) {
        ClientMapper clientMapper = new ClientMapperBuilder(getSession()).build();
        ClientDao clientDao = clientMapper.clientDao();
        clientDao.remove(item);
    }

    @Override
    public void update(UUID id, Client item) {
        ClientMapper clientMapper = new ClientMapperBuilder(getSession()).build();
        ClientDao clientDao = clientMapper.clientDao();
        clientDao.update(item, id);
    }

    @Override
    public Client find(UUID id) {
        ClientMapper clientMapper = new ClientMapperBuilder(getSession()).build();
        ClientDao clientDao = clientMapper.clientDao();

        return clientDao.find(id);
    }

    @Override
    public List<Client> findAll() {
        ClientMapper clientMapper = new ClientMapperBuilder(getSession()).build();
        ClientDao clientDao = clientMapper.clientDao();

        return clientDao.findAll();
    }
}
