package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import managers.MovieManager;
import model.sub.Genre;

public class App {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    private static EntityManager em = factory.createEntityManager();

    public static void main(String[] args) {
        System.out.println("test");
        MovieManager movieManager = new MovieManager();
        movieManager.addMovie("test", Genre.HORROR, 150, 200, 300);
    }

    // docker desktop
    // abstracr entity z polem long version @Version
}
