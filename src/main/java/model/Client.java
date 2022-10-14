package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import model.sub.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
public class Client extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "number", column = @Column(name = "number_of_house"))
    })
    @NotNull
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ticket> listOfTickets = new ArrayList<>();

    public Client(String name, String surname, long id, Address address) throws WrongValueException {
        setName(name);
        setSurname(surname);
        this.id = id;
        this.address = address;
    }

    protected Client() {

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getId() {
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
