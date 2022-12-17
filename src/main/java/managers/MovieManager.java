package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import repositories.MovieRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class MovieManager {
    private MovieRepository repository;

    public MovieManager(MovieRepository repository) {
        if(repository != null) {
            this.repository = repository;
        }
    }

    public Movie addMovie(String title, String genre, int ageRestriction, int durationInMinutes,
                          int seatLimit) {
        Movie movie = new Movie(title,genre,ageRestriction, durationInMinutes,seatLimit);
        this.repository.add(movie);
        return movie;
    }

    public void removeMovie(String uuid) throws MovieNotFoundException {
        try {
            Movie movieToRemove = repository.find(UUID.fromString(uuid));
            repository.remove(movieToRemove);
        } catch (NullPointerException e) {
            throw new MovieNotFoundException();
        }
    }

    public Movie findMovie(String uuid) throws MovieNotFoundException {
        try {
            return repository.find(UUID.fromString(uuid));
        } catch (NullPointerException e) {
            throw new MovieNotFoundException();
        }
    }

    public List<Movie> findAllMovies() {
        return repository.findAll();
    }

    public void updateMovie(String uuid, Movie movie) {
        Objects.requireNonNull(movie);
        repository.update(UUID.fromString(uuid), movie);
    }


}
