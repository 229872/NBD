package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import model.sub.Genre;
import repositories.Repository;

import java.util.function.Predicate;

public class MovieManager {
    private Repository<Movie> repository;

    public MovieManager() {
        this.repository = new Repository<>();
    }

    public MovieManager(Repository<Movie> repository) {
        this();
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Movie addMovie(String title, Genre genre, int ageRestriction, int durationInMinutes,
                          int seatLimit) {
        Movie movie = new Movie(title,genre,ageRestriction, durationInMinutes,seatLimit);
        this.repository.add(movie);
        return movie;
    }

    public boolean removeMovie(Predicate<Movie> predicate) throws MovieNotFoundException {
        Movie movieToRemove = repository.find(predicate);
        if(movieToRemove != null) {
            return repository.remove(movieToRemove);
        } else {
            throw new MovieNotFoundException();
        }
    }

    public Movie findMovie(Predicate<Movie> predicate) throws MovieNotFoundException {
        Movie found = repository.find(predicate);
        if(found != null) {
            return found;
        } else {
            throw new MovieNotFoundException();
        }
    }



}
