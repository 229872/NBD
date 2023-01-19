package model;

import exceptions.WrongValueException;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;


public abstract class Ticket extends AbstractEntity {

    @BsonProperty("base_price")
    private double basePrice;

    @BsonProperty("seat")
    private int seat;

    @BsonProperty("client")
    private Client client;

    @BsonProperty("movie")
    private Movie movie;

    @BsonCreator
    public Ticket(
            @BsonProperty("uuid") UniqueId id,
            @BsonProperty("base_price") double basePrice,
            @BsonProperty("seat") int seat,
            @BsonProperty("client") Client client,
            @BsonProperty("movie") Movie movie
        ) {
        super(id);
        this.basePrice = basePrice;
        this.seat = seat;
        this.client = client;
        this.movie = movie;
        this.movie.setSeatsTaken(this.movie.getSeatsTaken()+1);
    }

    public Ticket(double basePrice, int seat, Client client, Movie movie) {
        super(new UniqueId());
        this.basePrice = basePrice;
        this.seat = seat;
        this.client = client;
        this.movie = movie;
        this.movie.setSeatsTaken(this.movie.getSeatsTaken()+1);
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getSeat() {
        return seat;
    }

    public Client getClient() {
        return client;
    }

    public Movie getMovie() {
        return movie;
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

    public abstract double getTicketPrice();

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + getUuid().toString() +
                ", basePrice=" + basePrice +
                ", seat=" + seat +
                ", client=" + client +
                ", movie=" + movie +
                '}';
    }
}
