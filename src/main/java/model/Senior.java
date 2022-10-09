package model;

import exceptions.WrongValueException;

public class Senior extends Ticket {
    private String seniorIDCard;
    private int age;

    public Senior(int id, double basePrice, int seat, Client client, Movie movie,
                  String seniorIDCard, int age) throws WrongValueException {
        super(id, basePrice, seat, client, movie);
        setSeniorIDCard(seniorIDCard);
        setAge(age);
    }

    public String getSeniorIDCard() {
        return seniorIDCard;
    }

    public void setSeniorIDCard(String seniorIDCard) throws WrongValueException {
        if(!seniorIDCard.isBlank()) {
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
