package model;

import org.bson.codecs.pojo.annotations.BsonProperty;

public abstract class AbstractEntity implements AutoCloseable {

    @BsonProperty("uuid")
    private final UniqueId id;

    public AbstractEntity(UniqueId id) {
        this.id = id;
    }

    public UniqueId getUuid() {
        return id;
    }
}
