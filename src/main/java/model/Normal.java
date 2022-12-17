//package model;
//
//import exceptions.WrongValueException;
//import org.bson.codecs.pojo.annotations.BsonDiscriminator;
//
//@BsonDiscriminator(key = "type", value = "normal")
//public class Normal extends Ticket {
//
//    public Normal(double basePrice, int seat, Client client, Movie movie) throws WrongValueException {
//        super(new UniqueId() ,basePrice, seat, client, movie);
//    }
//
//    @Override
//    public double getTicketPrice() {
//        return getBasePrice();
//    }
//
//    @Override
//    public void close() throws Exception {
//
//    }
//}
