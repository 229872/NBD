package model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;
import java.util.UUID;

public class UniqueId {
    private UUID uuid;

    @JsonCreator
    public UniqueId() {
        this.uuid = UUID.randomUUID();
    }

    public UniqueId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUniqueId(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid.toString();
    }

}
