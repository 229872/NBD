package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import exceptions.WrongValueException;

import java.util.Objects;
import java.util.UUID;

@Entity(defaultKeyspace = "cinema")
@CqlName("tickets")
@PropertyStrategy(mutable = false)
public class Ticket {

    @PartitionKey
    private UUID ticketId;
    private double basePrice;
    private int seat;
    private UUID clientId;
    private UUID movieId;
    private String discriminator;


    public Ticket(double basePrice, UUID clientId, UUID movieId, String discriminator, int seat) {
        this.ticketId = UUID.randomUUID();
        this.basePrice = basePrice;
        this.seat = seat;
        this.clientId = clientId;
        this.movieId = movieId;
        this.discriminator = discriminator;
    }

    public Ticket(UUID ticketId, double basePrice, UUID clientId, UUID movieId, String discriminator, int seat) {
        this.ticketId = ticketId;
        this.basePrice = basePrice;
        this.seat = seat;
        this.clientId = clientId;
        this.movieId = movieId;
        this.discriminator = discriminator;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public int getSeat() {
        return seat;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    private void setSeat(int seat) throws WrongValueException {
        if(seat > 0) {
            this.seat = seat;
        } else {
            throw new WrongValueException("Seat cannot be 0 or less");
        }
    }

    private void setBasePrice(double basePrice) throws WrongValueException {
        //Sometimes we can give ticket for free
        if(basePrice >= 0) {
            this.basePrice = basePrice;
        } else {
            throw new WrongValueException("Base price cannot be negative");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.basePrice, basePrice) == 0 && seat == ticket.seat && Objects.equals(clientId, ticket.clientId) && Objects.equals(movieId, ticket.movieId) && discriminator.equals(ticket.discriminator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePrice, seat, clientId, movieId, discriminator);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" +   ticketId +
                "basePrice=" + basePrice +
                ", seat=" + seat +
                ", clientId=" + clientId +
                ", movieId=" + movieId +
                ", discriminator='" + discriminator + '\'' +
                '}';
    }
}
