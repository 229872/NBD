package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import model.sub.Genre;

@Entity
@Access(AccessType.FIELD)
public class Movie extends AbstractEntity {

    private static int movieCounter = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotEmpty
    private int ageRestriction;

    @NotEmpty
    private int durationInMinutes;

    @NotEmpty
    private int seatLimit;

    public Movie(String title, Genre genre, int ageRestriction, int durationInMinutes, int seatLimit) {
        this.id = ++movieCounter;
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getSeatLimit() {
        return seatLimit;
    }

    public int getId() {
        return id;
    }

}
