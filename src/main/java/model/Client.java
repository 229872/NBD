package model;

import exceptions.WrongValueException;
import model.sub.Address;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String surname;
    private final String id;
    private final Address address;
    private final List<Ticket> listOfTickets = new ArrayList<>();

    public Client(String name, String surname, String id, Address address) throws WrongValueException {
        setName(name);
        setSurname(surname);
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
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

    public void addTicket(Ticket ticket) {
        listOfTickets.add(ticket);
    }

    public List<Ticket> getListOfTickets() {
        return new ArrayList<>(listOfTickets);
    }
}
