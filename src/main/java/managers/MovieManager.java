package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import model.UniqueId;
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
        Movie movieToRemove = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(movieToRemove != null) {
            repository.remove(movieToRemove);
        } else {
            throw new MovieNotFoundException();
        }
    }

    public Movie findMovie(String uuid) throws MovieNotFoundException {
        Movie found = repository.find(new UniqueId(UUID.fromString(uuid)));
        if(found != null) {
            return found;
        } else {
            throw new MovieNotFoundException();
        }
    }

    public List<Movie> findMovieByTitle(String title) throws MovieNotFoundException {
        List<Movie> found = repository.findByName(title);
        if(!found.isEmpty()) {
            return found;
        } else {
            throw new MovieNotFoundException();
        }
    }

    public void updateMovie(String uuid, String title, String genre, int ageRestriction,
                            int durationInMinutes, int seatLimit ) {
        Movie check = repository.find(new UniqueId(UUID.fromString(uuid)));
        Objects.requireNonNull(check);
        Movie movie = new Movie(title, genre, ageRestriction, durationInMinutes, seatLimit);
        repository.update(new UniqueId(UUID.fromString(uuid)) ,movie);
    }



}
