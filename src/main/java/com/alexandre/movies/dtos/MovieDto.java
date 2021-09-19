package com.alexandre.movies.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

	private Long id;

	@NotEmpty(message = "Title cannot be empty")
	private String title;

	@NotNull(message = "Release‌ must be defined")
	private LocalDate release‌;

	@NotEmpty(message = "Genre cannot be empty.")
	private String genre;

	@NotEmpty(message = "Overview cannot be empty.")
	private String overview;

	@Range(min = 1, max = 5, message = "Rarating must be between 1 and 5")
	private Integer rarating;

}
