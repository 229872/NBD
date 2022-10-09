package model;

import exceptions.WrongValueException;

public class Normal extends Ticket {
    public Normal(int id, double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        super(id, basePrice, seat, client, movie);
    }

    @Override
    public double getTicketPrice() {
        return getBasePrice();
    }
}
