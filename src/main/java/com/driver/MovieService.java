package com.driver;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service

public class MovieService {

    MovieRepository movieRepository = new MovieRepository();

    public void addMovie(@RequestBody Movie movie) {
        movieRepository.saveMovie(movie);
    }

    public void addDirector(@RequestBody Director director) {
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(@RequestParam String movieName, @RequestParam String directorName) {
        movieRepository.saveMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(@RequestParam String name) {
        Optional<Movie> movieOptional = movieRepository.getMovieByName(name);
        if (movieOptional.isEmpty()){
            throw new MovieNotFoundException(name);
        }
        return movieOptional.get();
    }

    public Director getDirectorByName(@RequestParam String name) {
        Optional<Director> directorOptional = movieRepository.getDirectorByName(name);
        if (directorOptional.isEmpty()){
            throw new DirectorNotFoundException(name);
        }
        return directorOptional.get();
    }

    public List<String> getMoviesByItsDirectorName(@RequestParam String directorName) {
        return movieRepository.getMoviesByItsDirectorName(directorName);
    }

    public List<String> findAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirectorByName(@RequestParam String directorName) {
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors() {
        List<String> allDirectorsList = movieRepository.getAllDirectors();
        for (String directorName : allDirectorsList){
            deleteDirectorByName(directorName);
        }
    }


    //getMoviesByDirectorName
    //findAllMovies
    //deleteDirectorByName
    //deleteAllDirectors
}
