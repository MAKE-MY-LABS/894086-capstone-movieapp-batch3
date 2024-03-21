package com.capstone.movieapp.repository;

import com.capstone.movieapp.service.MovieServiceImpl;
import com.capstone.movieapp.model.Movie;
import com.capstone.movieapp.exceptions.MovielAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MovieServiceImplTest {

    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies() {
        Movie movie = new Movie();
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));

        assertEquals(Arrays.asList(movie), movieService.getAllMovies());
    }

    @Test
    public void testGetMovieById() {
        Movie movie = new Movie();
        when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

        assertEquals(Optional.of(movie), movieService.getMovieById("1"));
    }

    @Test
    public void testSaveMovie() throws MovielAlreadyExistsException {
        Movie movie = new Movie();
        when(movieRepository.existsById(String.valueOf(movie.getId()))).thenReturn(false);
        when(movieRepository.save(movie)).thenReturn(movie);

        assertEquals(movie, movieService.saveMovie(movie));
    }

    @Test
    public void testSaveMovieThrowsException() {
        Movie movie = new Movie();
        when(movieRepository.existsById(String.valueOf(movie.getId()))).thenReturn(true);

        assertThrows(MovielAlreadyExistsException.class, () -> movieService.saveMovie(movie));
    }

    // Note: For the methods `deleteMovieById`, `searchLatestMovies` and `fetchAllMovies`, 
    // you would need to mock the behavior of the `RestTemplate` class, which can be complex and is generally not recommended. 
    // Consider refactoring your code to make it more testable, for example by extracting the HTTP requests into a separate class.
}