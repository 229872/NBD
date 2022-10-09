package model;

import model.sub.Genre;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    @Test
    public void movieCreationTest() {
        String title = "Star Wars I";
        Genre genre = Genre.SCI_FI;
        int age = 13;
        int duration = 160;
        int seatLimit = 140;
        Movie movie = new Movie(title,genre,age,duration,seatLimit);
        Movie movie2 = new Movie(title,genre,age,duration,seatLimit);

        assertEquals(title,movie.getTitle());
        assertEquals(genre,movie.getGenre());
        assertEquals(seatLimit, movie.getSeatLimit());
        assertEquals(age, movie.getAgeRestriction());
        assertEquals(duration, movie.getDurationInMinutes());
        assertEquals(1,movie.getId());
        assertEquals(2,movie2.getId());
    }
}
