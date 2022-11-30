package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Objects;

public class Movie extends AbstractEntity {

    @BsonProperty("movie_title")
    private String title;
    @BsonProperty("movie_genre")
    private String genre;
    @BsonProperty("age_restriction")
    private int ageRestriction;
    @BsonProperty("duration_in_minutes")
    private int durationInMinutes;
    @BsonProperty("seat_limit")
    private int seatLimit;
    @BsonProperty("seats_taken")
    private int seatsTaken;

    @BsonCreator
    @JsonCreator
    public Movie(
            @JsonProperty("uuid") @BsonProperty("uuid") UniqueId id,
            @JsonProperty("movie_title") @BsonProperty("movie_title") String title,
            @JsonProperty("movie_genre") @BsonProperty("movie_genre") String genre,
            @JsonProperty("age_restriction") @BsonProperty("age_restriction") int ageRestriction,
            @JsonProperty("duration_in_minutes") @BsonProperty("duration_in_minutes") int durationInMinutes,
            @JsonProperty("seat_limit") @BsonProperty("seat_limit") int seatLimit,
            @JsonProperty("seats_taken") @BsonProperty("seats_taken") int seatsTaken
    ) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
        this.seatsTaken = seatsTaken;
    }

    public Movie(String title, String genre, int ageRestriction, int durationInMinutes, int seatLimit) {
        super(new UniqueId());
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
        this.seatsTaken = 0;
    }

    public int getSeatsTaken() {
        return seatsTaken;
    }

    public void setSeatsTaken(int seatsTaken) {
        this.seatsTaken = seatsTaken;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
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

    public void setGenre(String genre) {
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
                ", uuid=" + getUuid() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (ageRestriction != movie.ageRestriction) return false;
        if (durationInMinutes != movie.durationInMinutes) return false;
        if (seatLimit != movie.seatLimit) return false;
        if (seatsTaken != movie.seatsTaken) return false;
        if (!Objects.equals(title, movie.title)) return false;
        return Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + ageRestriction;
        result = 31 * result + durationInMinutes;
        result = 31 * result + seatLimit;
        result = 31 * result + seatsTaken;
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}
