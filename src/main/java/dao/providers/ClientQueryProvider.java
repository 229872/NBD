package dao.providers;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.EntityHelper;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import model.Client;
import model.sub.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class ClientQueryProvider {
    private final CqlSession session;

    private EntityHelper<Client> clientEntityHelper;

    public ClientQueryProvider(MapperContext ctx, EntityHelper<Client> clientEntityHelper) {
        this.session = ctx.getSession();
        this.clientEntityHelper = clientEntityHelper;
    }

    public void create(Client client) {
        session.execute(QueryBuilder.
                insertInto("clients")
                .value("id", literal(client.getId()))
                .value("name", literal(client.getName()))
                .value("surname", literal(client.getSurname()))
                .value("country", literal(client.getAddress().getCountry()))
                .value("city", literal(client.getAddress().getCity()))
                .value("street", literal(client.getAddress().getStreet()))
                .value("number", literal(client.getAddress().getNumber()))
                .build());
    }


    public Client find(UUID id) {
        Select selectClient = QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("clients"))
                .all()
                .where(Relation.column("id")
                .isEqualTo(literal(id)));

        return convertToClient(Objects.requireNonNull(session.execute(selectClient.build()).one()));
    }

    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();
        session.execute(QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("clients"))
                .all()
                .build())
                .forEach(row -> result.add(convertToClient(row)));

        return result;
    }

    private Client convertToClient(Row client) {
        return new Client(
                client.getUuid("id"),
                client.getString("name"),
                client.getString("surname"),
                new Address(client.getString("country"),
                      client.getString("city"),
                      client.getString("street"),
                      client.getInt("number"))
        );
    }

}
