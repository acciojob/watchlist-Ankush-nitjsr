package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieService {

    MovieRepository movieRepository = new MovieRepository();

    public void addMovie(Movie movie) {
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(String movieName, String directorName) {
        movieRepository.saveMovieDirectorPair(movieName, directorName);
    }

    public Movie getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies() {
        return movieRepository.getAllMovies();
    }

    public void deleteDirectorByName(String directorName) {
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
