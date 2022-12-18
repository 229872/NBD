package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import exceptions.WrongValueException;

import java.util.UUID;


@Entity(defaultKeyspace = "cinema")
@CqlName("tickets")
@PropertyStrategy(mutable = false)
public class Senior extends Ticket {

    private long seniorIDCard;
    private int age;

    public Senior(UUID id, long seniorIDCard, int age, double basePrice, UUID clientId, UUID movieId, String discriminator, int seat) {
        super(id, basePrice, clientId, movieId, discriminator, seat);
        this.seniorIDCard = seniorIDCard;
        this.age = age;
    }

    public Senior(double basePrice, int seat, UUID clientId, UUID movieId, long seniorIDCard, int age) {
        super(basePrice, clientId, movieId, "senior", seat);
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

//    public double getTicketPrice() {
//        if(age >= 50) {
//            double price = getBasePrice() - getBasePrice() * Math.pow(age,2) * 0.0001;
//            price = Math.max(price, getBasePrice() * 0.25);
//            return price;
//        } else {
//            return getBasePrice();
//        }
//    }

}
