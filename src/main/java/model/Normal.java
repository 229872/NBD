//package model;
//
//import exceptions.WrongValueException;
//import jakarta.persistence.*;
//
//@Entity
//@Access(AccessType.FIELD)
//@DiscriminatorValue("normal")
//public class Normal extends Ticket {
//    public Normal(double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
//        super(basePrice, seat, client, movie);
//    }
//
//    protected Normal() {
//
//    }
//
//    @Override
//    public double getTicketPrice() {
//        return getBasePrice();
//    }
//}
