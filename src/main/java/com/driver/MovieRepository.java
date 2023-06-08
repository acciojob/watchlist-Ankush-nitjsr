package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository

public class MovieRepository {
    private Map<String, Movie> movieMap;
    private Map<String, Director> directorMap;
    private Map<String, List<String>> directorMovieMap;
    // Pair is : DirectorName, List of Movie names

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMap = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        String key = movie.getName();
        movieMap.put(key, movie);
    }
    public Movie getMovieByName(String movieName){
        return movieMap.get(movieName);
    }


    public void saveDirector(Director director) {
        String key = director.getName();
        directorMap.put(key, director);
    }

    public Director getDirectorByName(String directorName) {
        return directorMap.get(directorName);
    }

    public void saveMovieDirectorPair(String movieName, String directorName) {
        if (movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
            movieMap.put(movieName, movieMap.get(movieName));
            directorMap.put(directorName, directorMap.get(directorName));
            List<String> currentMoviesByDirector = new ArrayList<>();
            if (directorMovieMap.containsKey(directorName)){
                currentMoviesByDirector = directorMovieMap.get(directorName);
                currentMoviesByDirector.add(movieName);
                directorMovieMap.put(directorName, currentMoviesByDirector);
            }
        }
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMap.containsKey(director)) moviesList = directorMovieMap.get(director);
        return moviesList;
    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String directorName){
        List<String> movies = new ArrayList<>();
        if (directorMovieMap.containsKey(directorName)) {
            // 1. find all movies from pair by director name
            movies = directorMovieMap.get(directorName);

            // 2. Deleting all movies from movieMap by using movieName
            for (String movie : movies) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }
            // 3. Deleting the pair
            directorMovieMap.remove(directorName);
        }

        // 4. Deleting director from directorMap
        if (directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
    }

    public List<String> getAllDirectors() {
        return new ArrayList<>(directorMap.keySet());
    }
}
