package com.alexandre.movies.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {

	private Long id;
	private String title;
	private LocalDate release‌;
	private String genre;
	private String overview;
	private Integer rarating;

}
