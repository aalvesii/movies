package com.alexandre.movies.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.services.MovieService;

@RestController
@RequestMapping("/stats")
public class StatsController {

	@Autowired
	private MovieService service;

	@GetMapping("/movie-count")
	public ResponseEntity<String> getAll() {
		long count = service.getMovieCount();
		return ResponseEntity.ok(String.format("Movie cout %d", count));
	}

	@PostMapping("/movie-count-by-genre")
	public ResponseEntity<String> save(@RequestBody Movie movie) {
		long count = service.getMovieByGenre(movie.getGenre());
		return ResponseEntity.ok(String.format("%s Movies %d", movie.getGenre(), count));
	}

}
