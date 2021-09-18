package com.alexandre.movies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieService service;

	@GetMapping
	public List<Movie> getAll() {
		return service.getAll();
	}
	
	@PostMapping
	public void save(Movie movie) {
		service.save(movie);
	}

}
