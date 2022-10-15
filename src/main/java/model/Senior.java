package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("senior")
public class Senior extends Ticket {

    @Column(unique = true)
    private long seniorIDCard;

    @NotEmpty
    private int age;

    public Senior(double basePrice, int seat, Client client, Movie movie,
                  long seniorIDCard, int age) throws WrongValueException {
        super(basePrice, seat, client, movie);
        setSeniorIDCard(seniorIDCard);
        setAge(age);
    }

    protected Senior() {

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
}
