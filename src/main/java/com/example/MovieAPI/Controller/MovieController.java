package com.example.MovieAPI.Controller;

import com.example.MovieAPI.Entity.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private Map<Long, Movie> movieDB = new HashMap<>();

    @GetMapping("/all-movies")
    public ResponseEntity<List<Movie>> getALlMovies()
    {
        return ResponseEntity.ok(new ArrayList<>(movieDB.values()));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie)
    {
        movieDB.put(movie.getId(), movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id)
    {
        Movie existingMovie = movieDB.get(id);
        if(existingMovie==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(existingMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id)
    {
        Movie existingMovie = movieDB.remove(id);
        if(existingMovie==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(existingMovie);
    }

    @PutMapping("/{id}/edit-movie-details")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie)
    {
        Movie existingMovie = movieDB.get(id);
        if(existingMovie==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        movieDB.put(id, movie);
        return ResponseEntity.ok(movie);
    }
}
