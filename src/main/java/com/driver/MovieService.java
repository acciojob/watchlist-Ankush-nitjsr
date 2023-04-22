package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Boolean addMovie(Movie movie) {
        Optional<Movie> movieOptional = movieRepository.getMovieByName(movie.getName());
        if (movieOptional.isPresent()){
            throw new MovieAlreadyExistsException(movie.getName());
        }
        return movieRepository.addMovie(movie);
    }

    public Boolean addDirector(Director director) {
        Optional<Director> directorOptional = movieRepository.getDirectorByName(director.getName());
        if (directorOptional.isPresent()){
            throw new MovieAlreadyExistsException(director.getName());
        }
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String movieName, String directorName) {
        return movieRepository.addMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String name) {
        Optional<Movie> movieOptional = movieRepository.getMovieByName(name);
        if (movieOptional.isEmpty()){
            throw new MovieNotFoundException(name);
        }
        return movieOptional.get();
    }

    public Director getDirectorByName(String name) {
        Optional<Director> directorOptional = movieRepository.getDirectorByName(name);
        if (directorOptional.isEmpty()){
            throw new DirectorNotFoundException(name);
        }
        return directorOptional.get();
    }

    public List<String> getMoviesByItsDirectorName(String directorName) {
        return movieRepository.getMoviesByItsDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.getAllMovies();
    }


    //getMoviesByDirectorName
    //findAllMovies
    //deleteDirectorByName
    //deleteAllDirectors
}
