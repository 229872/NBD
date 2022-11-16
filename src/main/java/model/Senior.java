package model;

import exceptions.WrongValueException;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Senior extends Ticket {

    @BsonProperty("senior_id")
    private long seniorIDCard;

    @BsonProperty("age")
    private int age;

    @BsonCreator
    public Senior(
            @BsonProperty("uuid") UniqueId id,
            @BsonProperty("base_price") double basePrice,
            @BsonProperty("seat") int seat,
            @BsonProperty("client") Client client,
            @BsonProperty("movie") Movie movie,
            @BsonProperty("senior_id") long seniorIDCard,
            @BsonProperty("age") int age
        ) throws WrongValueException {

        super(id, basePrice, seat, client, movie);
        setSeniorIDCard(seniorIDCard);
        setAge(age);
    }

    public Senior(double basePrice, int seat, Client client, Movie movie, long seniorIDCard, int age) {
        super(new UniqueId(), basePrice, seat, client, movie);
        this.seniorIDCard = seniorIDCard;
        this.age = age;
    }

    public long getSeniorIDCard() {
        return seniorIDCard;
    }

    public void setSeniorIDCard(long seniorIDCard) throws WrongValueException {
        if(seniorIDCard != 0) {
            this.seniorIDCard = seniorIDCard;
        } else {
            throw new WrongValueException("Senior ID card cannot be blank");
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws WrongValueException {
        if(age >= 60) {
            this.age = age;
        } else {
            throw new WrongValueException("Senior's age cannot be under 60");
        }
    }

    @Override
    public double getTicketPrice() {
        if(age >= 50) {
            double price = getBasePrice() - getBasePrice() * Math.pow(age,2) * 0.0001;
            price = Math.max(price, getBasePrice() * 0.25);
            return price;
        } else {
            return getBasePrice();
        }
    }

    @Override
    public void close() throws Exception {

    }
}
