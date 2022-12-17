package repositories;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;

import java.net.InetSocketAddress;

public abstract class AbstractRepository<T> implements AutoCloseable, Repository<T> {

    private static CqlSession session;
    private static final String keyspaceName = "cinema";

    static {
        initSession();
        createKeyspace();
    }

    private static void initSession() {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("cassandra1", 9042))
                .addContactPoint(new InetSocketAddress("cassandra2", 9043))
                .withLocalDatacenter("dc1")
                .withAuthCredentials("cassandra", "cassandrapassword")
                .withKeyspace(keyspaceName)
                .build();
    }

    private static void createKeyspace() {
        session.execute(SchemaBuilder.createKeyspace(CqlIdentifier.fromCql(keyspaceName))
                .ifNotExists()
                .withSimpleStrategy(2)
                .withDurableWrites(true).build());
    }

    private static void dropKeyspace() {
        session.execute(SchemaBuilder.dropKeyspace(CqlIdentifier.fromCql(keyspaceName))
                .ifExists()
                .build());
    }

    public static CqlSession getSession() {
        return session;

    }

    public abstract void createTable();

    @Override
    public void close() {
        session.close();
    }
}
