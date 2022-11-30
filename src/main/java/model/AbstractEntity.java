package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonProperty;

public abstract class AbstractEntity implements AutoCloseable {

    @BsonProperty("uuid")
    @JsonProperty
    private final UniqueId id;

    @JsonCreator
    public AbstractEntity(@JsonProperty("uuid") UniqueId id) {
        this.id = id;
    }

    public UniqueId getUuid() {
        return id;
    }
}
