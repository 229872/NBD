package model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Column(name = "uuid")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Version
    private long version;
}
