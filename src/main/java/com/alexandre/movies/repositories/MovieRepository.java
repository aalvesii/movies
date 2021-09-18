package com.alexandre.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexandre.movies.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
