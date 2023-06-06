package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity addMovie(@RequestBody Movie movie){
            movieService.addMovie(movie);
            return new ResponseEntity("New Movie added successfully", HttpStatus.CREATED);
    }
    //addDirector()
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
            movieService.addDirector(director);
            return new ResponseEntity("New Director added successfully", HttpStatus.CREATED);
    }
    //addMovieDirectorPair()
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
            String added = movieService.addMovieDirectorPair(movieName, directorName);
            return new ResponseEntity(added, HttpStatus.CREATED);
    }
    //getMovieByName
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
            Movie movie = movieService.getMovieByName(name);
            return new ResponseEntity(movie, HttpStatus.OK);
    }
    //getDirectorByName
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
            Director director = movieService.getDirectorByName(name);
            return new ResponseEntity(director, HttpStatus.OK);
    }
    //getMoviesByDirectorName
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName){
            List<String> ans = movieService.getMoviesByItsDirectorName(directorName);
            return new ResponseEntity(ans,HttpStatus.OK);
    }
    //findAllMovies
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> ans = movieService.findAllMovies();
        return new ResponseEntity(ans,HttpStatus.OK);
    }
    //deleteDirectorByName
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String directorName){
            movieService.deleteDirectorByName(directorName);
            return new ResponseEntity("director deleted successfully", HttpStatus.OK);
    }
    //deleteAllDirectors
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
            movieService.deleteAllDirectors();
            return new ResponseEntity("All director deleted successfully", HttpStatus.OK);
    }

}
