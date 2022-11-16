import exceptions.ClientNotFoundException;
import exceptions.MovieNotFoundException;
import exceptions.WrongTicketException;
import exceptions.WrongValueException;
import managers.ClientManager;
import managers.MovieManager;
import managers.TicketManager;
import model.Client;
import model.Movie;
import model.UniqueId;
import model.sub.Genre;
import model.sub.SchoolType;
import repositories.ClientRepository;
import repositories.MovieRepository;
import repositories.TicketRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) throws MovieNotFoundException, WrongValueException, ClientNotFoundException, WrongTicketException {

       System.out.println("test");
    }
}
