package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    @Test
    public void movieCreationTest() {
        String title = "Star Wars I";
        String genre = "SCI_FI";
        int age = 13;
        int duration = 160;
        int seatLimit = 140;
        Movie movie = new Movie(title,genre,age,duration,seatLimit);

        assertEquals(title,movie.getTitle());
        assertEquals(genre,movie.getGenre());
        assertEquals(seatLimit, movie.getSeatLimit());
        assertEquals(age, movie.getAgeRestriction());
        assertEquals(duration, movie.getDurationInMinutes());
    }
}
