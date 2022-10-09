package model;

import exceptions.WrongValueException;

public abstract class Ticket {
    private final int id;
    private double basePrice;
    private int seat;
    private final Client client;
    private final Movie movie;

    public Ticket(int id, double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
        this.id = id;
        setBasePrice(basePrice);
        setSeat(seat);
        this.client = client;
        this.movie = movie;
    }

    public int getId() {
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
}
