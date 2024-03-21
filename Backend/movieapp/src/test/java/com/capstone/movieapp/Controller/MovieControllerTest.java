package com.capstone.movieapp.Controller;

import com.capstone.movieapp.controller.MoviesController;
import com.capstone.movieapp.model.MovieList;
import com.capstone.movieapp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MovieControllerTest {

    @InjectMocks
    MoviesController moviesController;

    @Mock
    MovieService movieService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetAllMovies() {
        MovieList movieList = new MovieList();
        when(movieService.fetchAllMovies()).thenReturn(movieList);

        ResponseEntity<MovieList> response = moviesController.getAllMovies();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(movieList, response.getBody());
    }

    @Test
    public void testSearchLatestMovies() {
        String keyword = "test";
        MovieList movieList = new MovieList();
        when(movieService.searchLatestMovies(keyword)).thenReturn(movieList);

        ResponseEntity<MovieList> response = moviesController.searchLatestMovies(keyword);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(movieList, response.getBody());
    }
}