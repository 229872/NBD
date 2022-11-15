package managers;

import exceptions.MovieNotFoundException;
import model.Movie;
import model.UniqueId;
import model.sub.Genre;
import repositories.MovieRepository;
import repositories.Repository;

import java.util.UUID;


public class MovieManager {
    private MovieRepository repository;

//    public MovieManager() {
//        this.repository = RepositoryFactory.createRepository(Movie.class);
//    }


    public MovieManager(MovieRepository repository) {
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

    public void removeMovie(UniqueId uuid) throws MovieNotFoundException {
        Movie movieToRemove = repository.find(uuid);
        if(movieToRemove != null) {
            repository.remove(movieToRemove);
        } else {
            throw new MovieNotFoundException();
        }
    }

    public Movie findMovie(UniqueId uuid) throws MovieNotFoundException {
        Movie found = repository.find(uuid);
        if(found != null) {
            return found;
        } else {
            throw new MovieNotFoundException();
        }
    }

    public Movie findMovieByTitle(String title) throws MovieNotFoundException {
        Movie found = repository.findByName(title);
        if(found != null) {
            return found;
        } else {
            throw new MovieNotFoundException();
        }
    }



}
