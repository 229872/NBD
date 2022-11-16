package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;


public class Normal extends Ticket {

    public Normal(double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        super(basePrice, seat, client, movie);
    }

    @Override
    public double getTicketPrice() {
        return getBasePrice();
    }

    @Override
    public void close() throws Exception {

    }
}
