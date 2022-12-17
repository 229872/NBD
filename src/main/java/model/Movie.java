package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;

import java.util.Objects;
import java.util.UUID;

@Entity(defaultKeyspace = "cinema")
@CqlName("movies")
@PropertyStrategy(mutable = false)
public class Movie extends AbstractEntity{

    private String title;
    private String genre;
    private int ageRestriction;

    private int durationInMinutes;

    private int seatLimit;

    public Movie(String title, String genre, int ageRestriction, int durationInMinutes, int seatLimit) {
        super();
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
    }

    public Movie(UUID id, String title, String genre, int ageRestriction, int durationInMinutes, int seatLimit) {
        super(id);
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.durationInMinutes = durationInMinutes;
        this.seatLimit = seatLimit;
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
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", ageRestriction=" + ageRestriction +
                ", durationInMinutes=" + durationInMinutes +
                ", seatLimit=" + seatLimit +
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
        return result;
    }

}
