package model;

import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable {

    @PartitionKey
    private UUID id;

    public AbstractEntity() {
        this.id = UUID.randomUUID();
    }

    public AbstractEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
