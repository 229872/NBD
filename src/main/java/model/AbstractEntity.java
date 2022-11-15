package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity implements AutoCloseable {

    @BsonProperty("_id")
    private final UniqueId id;

    public AbstractEntity(UniqueId id) {
        this.id = id;
    }

    public UniqueId getUuid() {
        return id;
    }
}
