package com.alexandre.movies.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.movies.entities.Movie;
import com.alexandre.movies.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	/**
	 * List all movies
	 * 
	 * @return all movies
	 */
	public List<Movie> getAll() {
		return repository.findAll();
	}

	/**
	 * Save Movie
	 * 
	 * @param movie the Movie to be saved
	 * @return 
	 */
	public Movie save(Movie movie) {
		return repository.save(movie);
	}

	/**
	 * Updade Movie
	 * 
	 * @param id    the Movie id
	 * @param movie the Movie to be updated
	 * @return
	 */
	public Movie update(Long id, Movie movie) {
		Optional<Movie> optional = repository.findById(id);

		if (optional.isPresent()) {
			Movie movieDb = optional.get();

			if (StringUtils.isNoneEmpty(movie.getTitle()))
				movieDb.setTitle(movie.getTitle());
			if (movie.getRelease‌() != null)
				movieDb.setRelease‌(movie.getRelease‌());
			if (StringUtils.isNoneEmpty(movie.getGenre()))
				movieDb.setGenre(movie.getGenre());
			if (StringUtils.isNoneEmpty(movie.getOverview()))
				movieDb.setOverview(movie.getOverview());
			if (movie.getRarating() != null)
				movieDb.setRarating(movie.getRarating());

			repository.save(movieDb);

			return movieDb;
		}

		return null;
	}

	/**
	 * Remove Movie by id
	 * 
	 * @param id the Movie id
	 */
	public void remove(Long id) {
		Optional<Movie> optional = repository.findById(id);

		if (optional.isPresent()) {
			repository.delete(optional.get());
		}
	}

	/**
	 * Count the number of Movies
	 * 
	 * @return movie count
	 */
	public long getMovieCount() {
		return repository.count();
	}

	/**
	 * Count the number of Movies by genre
	 * 
	 * @param genre the movies genre
	 * @return movie count
	 */
	public long getMovieByGenre(String genre) {
		return repository.countByGenre("%" + genre.toUpperCase() + "%");
	}

}