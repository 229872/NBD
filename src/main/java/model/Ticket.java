package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@DiscriminatorColumn(name = "type")
public abstract class Ticket extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double basePrice;

    private int seat;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    @NotNull
    private Client client;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;


    public Ticket(double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        setBasePrice(basePrice);
        setSeat(seat);
        this.client = client;
        this.movie = movie;
    }

    protected Ticket() {

    }

    public long getId() {
        return id;
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
                "id=" + id +
                ", basePrice=" + basePrice +
                ", seat=" + seat +
                ", client=" + client +
                ", movie=" + movie +
                '}';
    }
}
