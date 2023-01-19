package repositories;

import com.mongodb.*;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import repositories.codec.UniqueIdCodecProvider;

import java.util.List;

public abstract class AbstractRepository<T> implements AutoCloseable, Repository<T> {
    private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    private MongoCredential credential =
            MongoCredential.createCredential("nbd", "admin", "nbdpassword".toCharArray());
    private CodecRegistry pojoCodecRegistry =
            CodecRegistries.fromProviders(PojoCodecProvider.builder()
                            .automatic(true)
                            .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                            .build());
    private static MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    public AbstractRepository() {
        initConnection();
    }

    private void initConnection() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        CodecRegistries.fromProviders(new UniqueIdCodecProvider()),
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry
                ))
                .build();
        mongoClient = MongoClients.create(settings);
        mongoDatabase = mongoClient.getDatabase("admin");
        if(firstInstance)
            initDatabase();
        firstInstance = false;
    }

    private static boolean firstInstance = true;

    private void initDatabase() {
        try {
            mongoDatabase.createCollection("clients");
            mongoDatabase.createCollection("movies");
            mongoDatabase.createCollection("tickets");
        } catch (MongoCommandException e) {
            e.getErrorMessage();
        }
    }

    public MongoDatabase getDb() {
        return mongoDatabase;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static ClientSession startNewSession() {
        return getMongoClient().startSession();
    }

    public static TransactionOptions getTransactionOptions() {
        return TransactionOptions.builder()
                .readPreference(ReadPreference.primaryPreferred())
                .readConcern(ReadConcern.LOCAL)
                .writeConcern(WriteConcern.MAJORITY)
                .build();
    }

    @Override
    public void close() throws Exception {
        mongoClient.close();
    }
}
