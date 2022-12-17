package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import com.datastax.oss.driver.api.mapper.annotations.Transient;
import exceptions.WrongValueException;
import model.sub.Address;

import java.util.Objects;
import java.util.UUID;

@Entity(defaultKeyspace = "cinema")
@CqlName("clients")
@PropertyStrategy(mutable = false)
public class Client extends AbstractEntity {

    private String name;
    private String surname;

    @Transient
    private Address address;

    public Client(UUID id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public Client(String name, String surname, Address address) {
        super();
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public Client(UUID id, String name, String surname, Address address) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name)
                && surname.equals(client.surname)
                && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, address);
    }
}
