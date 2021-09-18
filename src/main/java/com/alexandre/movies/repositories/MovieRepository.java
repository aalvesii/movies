package com.alexandre.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alexandre.movies.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	/**
	 * Count the number of Movies by genre
	 * 
	 * @param genre the movies genre
	 * @return movie count
	 */
	@Query("select count(m) from Movie m where upper(m.genre) like ?1")
	long countByGenre(String genre);

}
