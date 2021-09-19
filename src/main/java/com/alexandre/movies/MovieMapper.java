package com.alexandre.movies;

import java.util.List;

import org.mapstruct.Mapper;

import com.alexandre.movies.dtos.MovieDto;
import com.alexandre.movies.entities.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {

	MovieDto map(Movie movie);

	Movie map(MovieDto movieDto);

	List<MovieDto> map(List<Movie> movies);

}
