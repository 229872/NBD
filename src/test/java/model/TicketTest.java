package model;

import exceptions.WrongValueException;
import model.sub.Address;
import model.sub.Genre;
import model.sub.SchoolType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TicketTest {
    @Test
    public void normalTicketCreationTest() throws WrongValueException {
        double basePrice = 15.20;
        int seat = 1;
        int id = 1;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Ticket ticket = new Normal(id, basePrice, seat, client, movie);

        assertEquals(id, ticket.getId());
        assertEquals(basePrice,ticket.getBasePrice(),0.00001);
        assertEquals(seat,ticket.getSeat());
        assertEquals(client,ticket.getClient());
        assertEquals(movie,ticket.getMovie());
    }

    @Test
    public void studentTicketCreationTest() throws WrongValueException {
        double basePrice = 15.20;
        int seat = 1;
        int id = 1;
        long studentId = 123;
        SchoolType schoolType = SchoolType.HIGH_SCHOOL;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Student ticket = new Student(id, basePrice, seat, client, movie, studentId, schoolType);

        assertEquals(id, ticket.getId());
        assertEquals(basePrice, ticket.getBasePrice(),0.00001);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
        assertEquals(studentId, ticket.getStudentIDCard());
        assertEquals(schoolType, ticket.getSchoolType());
    }

    @Test
    public void seniorTicketCreationTest() throws WrongValueException {
        double basePrice = 15.20;
        int seat = 1;
        int id = 1;
        long seniorId = 123;
        int age = 80;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Senior ticket = new Senior(id, basePrice, seat, client, movie, seniorId, age);

        assertEquals(id, ticket.getId());
        assertEquals(basePrice, ticket.getBasePrice(),0.00001);
        assertEquals(seat, ticket.getSeat());
        assertEquals(client, ticket.getClient());
        assertEquals(movie, ticket.getMovie());
        assertEquals(seniorId, ticket.getSeniorIDCard());
        assertEquals(age, ticket.getAge());
    }

    @Test
    public void normalGetTicketPriceTest() throws WrongValueException {
        double basePrice = 15.20;
        int seat = 1;
        int id = 1;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Ticket ticket = new Normal(id, basePrice, seat, client, movie);

        assertEquals(basePrice,ticket.getTicketPrice(),0.00001);
    }

    @Test
    public void seniorGetTicketPriceTest() throws WrongValueException {
        double basePrice = 14.0;
        int seat = 1;
        int id = 1;
        int age = 60;
        double price = basePrice - basePrice * Math.pow(age,2) * 0.0001;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Ticket ticket = new Senior(id, basePrice, seat, client, movie,123,age);

        assertEquals(price,ticket.getTicketPrice(),0.00001);
        age = 70;
        price = basePrice - basePrice * Math.pow(age,2) * 0.0001;
        Ticket ticket2 = new Senior(id, basePrice, seat, client, movie,123,age);
        assertEquals(price,ticket2.getTicketPrice(),0.00001);
        age = 90;
        Ticket ticket3 = new Senior(id, basePrice, seat, client, movie,123,age);
        assertEquals(3.5,ticket3.getTicketPrice(),0.00001);
    }

    @Test
    public void studentGetTicketPriceTest() throws WrongValueException {
        double basePrice = 14.0;
        int seat = 1;
        int id = 1;
        Address address = new Address("England", "London", "Sea road", 15);
        Movie movie = new Movie("Star Wars I", Genre.SCI_FI, 13,
                160, 140);
        Client client = new Client("John", "Doe", 123, address);
        Student student1 = new Student(id, basePrice, seat, client, movie, 123,
                SchoolType.PRIMARY_SCHOOL);
        Student student2 = new Student(id, basePrice, seat, client, movie, 123,
                SchoolType.HIGH_SCHOOL);
        Student student3 = new Student(id, basePrice, seat, client, movie, 123,
                SchoolType.STUDIES);

        double price = basePrice - basePrice * 0.3;
        assertEquals(price,student1.getTicketPrice(),0.00001);
        assertEquals(price,student2.getTicketPrice(),0.00001);
        price = basePrice - basePrice * 0.5;
        assertEquals(price,student3.getTicketPrice(),0.00001);
    }


}
