package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import model.sub.Genre;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public class Movie extends AbstractEntity {

    @BsonProperty("movie_title")
    private String title;
    @BsonProperty("movie_genre")
    private Genre genre;
    @BsonProperty("age_restriction")
    private int ageRestriction;
    @BsonProperty("duration_in_minutes")
    private int durationInMinutes;
    @BsonProperty("seat_limit")
    private int seatLimit;
    @BsonProperty("seats_taken")
    private int seatsTaken;

    @BsonCreator
    public Movie(
            @BsonProperty("_id") UUID uuid,
            @BsonProperty("movie_title") String title,
            @BsonProperty("movie_genre") Genre genre,
            @BsonProperty("age_restriction") int ageRestriction,
            @BsonProperty("duration_in_minutes") int durationInMinutes,
            @BsonProperty("seat_limit") int seatLimit,
            @BsonProperty("seats_taken") int seatsTaken
    ) {
        super(uuid);
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
        this.seatsTaken = seatsTaken;
    }

//    public Movie(String title, Genre genre, int ageRestriction, int durationInMinutes, int seatLimit) {
//        this.title = title;
//        this.genre = genre;
//        this.ageRestriction = ageRestriction;
//        this.durationInMinutes = durationInMinutes;
//        this.seatLimit = seatLimit;
//        this.seatsTaken = 0;
//    }
//
//    protected Movie() {
//
//    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setSeatLimit(int seatLimit) {
        this.seatLimit = seatLimit;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", ageRestriction=" + ageRestriction +
                ", durationInMinutes=" + durationInMinutes +
                ", seatLimit=" + seatLimit +
                '}';
    }
}
