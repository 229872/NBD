package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

import java.util.UUID;

@Entity(defaultKeyspace = "cinema")
@CqlName("tickets")
@PropertyStrategy(mutable = false)
public class Normal extends Ticket {

    public Normal(UUID id, double basePrice, UUID clientId, UUID movieId, String discriminator, int seat) {
        super(id, basePrice, clientId, movieId, discriminator, seat);
    }

    public Normal(double basePrice, int seat, UUID clientId, UUID movieId) {
        super(basePrice, clientId, movieId, "normal", seat);
    }


}
