package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import model.sub.Address;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Client extends AbstractEntity {

    @BsonProperty("client_name")
    private String name;
    @BsonProperty("client_surname")
    private String surname;
    @BsonProperty("address")
    private Address address;
//    @BsonProperty("list_of_tickets")
//    private List<Ticket> listOfTickets = new ArrayList<>();

    @BsonCreator
    public Client(@BsonProperty("_id") UniqueId id,
                  @BsonProperty("client_name") String name,
                  @BsonProperty("client_surname") String surname,
                  @BsonProperty("address") Address address
                  ) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Client(String name, String surname, Address address) throws WrongValueException {
        super(new UniqueId());
        setName(name);
        setSurname(surname);
        this.address = address;
    }
//
//    protected Client() {
//
//    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public Address getAddress() {
        return address;
    }

    public void setName(String name) throws WrongValueException {
        if(name != null && !name.isBlank()) {
            this.name = name;
        } else {
            throw new WrongValueException("Name cannot be blank");
        }

    }

    public void setSurname(String surname) throws WrongValueException {
        if(surname != null && !surname.isBlank()) {
            this.surname = surname;
        } else {
            throw new WrongValueException("Surname cannot be blank");
        }
    }

//    public void addTicket(Ticket ticket) {
//        listOfTickets.add(ticket);
//    }
//
//    public List<Ticket> getListOfTickets() {
//        return new ArrayList<>(listOfTickets);
//    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                '}';
    }
}
