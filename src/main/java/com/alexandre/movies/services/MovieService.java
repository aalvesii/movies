package com.alexandre.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;

	public List<Movie> getAll() {
		return repository.findAll();
	}

	public void save(Movie movie) {
		repository.save(movie);		
	}

}
