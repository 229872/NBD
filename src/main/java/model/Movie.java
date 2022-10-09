package model;

import model.sub.Genre;

public class Movie {
    private static int movieCounter = 0;

    private final int id;
    private final String title;
    private final Genre genre;
    private final int ageRestriction;
    private final int durationInMinutes;
    private final int seatLimit;

    public Movie(String title, Genre genre, int ageRestriction, int durationInMinutes, int seatLimit) {
        this.id = ++movieCounter;
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
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
