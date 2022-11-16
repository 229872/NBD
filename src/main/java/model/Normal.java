package model;

import exceptions.WrongValueException;

public class Normal extends Ticket {

    public Normal(double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        super(new UniqueId() ,basePrice, seat, client, movie);
    }

    @Override
    public double getTicketPrice() {
        return getBasePrice();
    }

    @Override
    public void close() throws Exception {

    }
}
