package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("normal")
public class Normal extends Ticket {
    public Normal(int id, double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        super(id, basePrice, seat, client, movie);
    }

    public Normal() {

    }

    @Override
    public double getTicketPrice() {
        return getBasePrice();
    }
}
