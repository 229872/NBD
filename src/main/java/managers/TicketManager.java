//package managers;
//
//import exceptions.TicketNotFoundException;
//import exceptions.WrongTicketException;
//import exceptions.WrongValueException;
//import model.*;
//import model.sub.SchoolType;
//import repositories.TicketRepository;
//import java.util.Objects;
//import java.util.UUID;
//
//public class TicketManager {
//    private TicketRepository repository;
//
//
//    public TicketManager(TicketRepository repository) {
//        if(repository != null) {
//            this.repository = repository;
//        }
//    }
//
//    public Ticket addNormalTicket(double basePrice, int seat, Client client,
//                                  Movie movie) throws WrongTicketException, WrongValueException {
//        boolean isTaken = repository.isSeatTaken(seat, movie);
//        if(!isTaken) {
//            Ticket ticket = new Normal(basePrice,seat,client,movie);
//            repository.add(ticket);
//            return ticket;
//        } else {
//            throw new WrongTicketException("Seat is already taken");
//        }
//
//    }
//
//    public Ticket addStudentTicket(double basePrice, int seat, Client client,
//                                   Movie movie, long studentIDCard, SchoolType schoolType) throws WrongValueException, WrongTicketException {
//
//        boolean isTaken = repository.isSeatTaken(seat, movie);
//        if(!isTaken) {
//            Ticket ticket = new Student(basePrice, seat, client, movie, studentIDCard, schoolType);
//            repository.add(ticket);
//            return ticket;
//        } else {
//            throw new WrongTicketException("Seat is already taken");
//        }
//
//    }
//
//    public Ticket addSeniorTicket(double basePrice, int seat, Client client,
//                                  Movie movie, long seniorIDCard, int age) throws WrongTicketException, WrongValueException {
//        boolean isTaken = repository.isSeatTaken(seat, movie);
//        if(!isTaken) {
//            Ticket ticket = new Senior(basePrice,seat,client,movie,seniorIDCard,age);
//            repository.add(ticket);
//            return ticket;
//        } else {
//            throw new WrongTicketException("Seat is already taken");
//        }
//    }
//
//    public Ticket findTicket(String uuid) throws TicketNotFoundException {
//        Ticket found = repository.find(new UniqueId(UUID.fromString(uuid)));
//        if(found != null) {
//            return found;
//        } else {
//            throw new TicketNotFoundException();
//        }
//    }
//
//    public void updateTicket(String uuid, Ticket ticket) {
//        Objects.requireNonNull(ticket);
//        repository.update(new UniqueId(UUID.fromString(uuid)), ticket);
//    }
//
//
//
//    public void removeTicket(String uuid) throws TicketNotFoundException {
//        Ticket found = repository.find(new UniqueId(UUID.fromString(uuid)));
//        if(found != null) {
//            repository.remove(found);
//        } else {
//            throw new TicketNotFoundException();
//        }
//    }
//}
