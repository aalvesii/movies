package com.alexandre.movies.services;

import java.util.List;
import java.util.Optional;

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

	public void update(Long id, Movie movie) {
		Optional<Movie> optional = repository.findById(id);

		if (optional.isPresent()) {
			Movie movieDb = optional.get();

			movieDb.setTitle(movie.getTitle());
			movieDb.setRelease‌(movie.getRelease‌());
			movieDb.setGenre(movie.getGenre());
			movieDb.setOverview(movie.getOverview());
			movieDb.setRarating(movie.getRarating());

			repository.save(movieDb);
		}
	}

	public void remove(Long id) {
		Optional<Movie> optional = repository.findById(id);

		if (optional.isPresent()) {
			repository.delete(optional.get());
		}
	}

	public long getMovieCount() {
		return repository.count();
	}

	public long getMovieByGenre(String genre) {
		return repository.countByGenre(genre);
	}

}
