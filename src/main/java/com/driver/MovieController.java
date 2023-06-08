package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieService movieService = new MovieService();

    //addMovie()
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
            movieService.addMovie(movie);
            return new ResponseEntity<>("New Movie added successfully", HttpStatus.CREATED);
    }
    //addDirector()
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
            movieService.addDirector(director);
            return new ResponseEntity<>("New Director added successfully", HttpStatus.CREATED);
    }
    //addMovieDirectorPair()
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
            movieService.createMovieDirectorPair(movieName, directorName);
            return new ResponseEntity("pair created successfully", HttpStatus.CREATED);
    }
    //getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity<>(movie, HttpStatus.OK);
    }
    //getDirectorByName
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
            Director director = movieService.getDirectorByName(name);
            return new ResponseEntity<>(director, HttpStatus.OK);
    }
    //getMoviesByDirectorName
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director){
        List<String> movies = movieService.findMoviesFromDirector(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }
    //findAllMovies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies,HttpStatus.OK);
    }
    //deleteDirectorByName
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
            movieService.deleteDirectorByName(directorName);
            return new ResponseEntity<>(directorName + " deleted successfully", HttpStatus.OK);
    }
    //deleteAllDirectors
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
            movieService.deleteAllDirectors();
            return new ResponseEntity<>("All directors deleted successfully", HttpStatus.OK);
    }

}
