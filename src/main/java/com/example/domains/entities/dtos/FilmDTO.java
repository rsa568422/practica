package com.example.domains.entities.dtos;

import java.util.LinkedList;

import com.example.domains.entities.Film;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
	
	private int filmId;
	
	private String title;

	private String description;

	private int length;

	private String rating;
	
	public static Film toEntity(FilmDTO dto) {
		Film entity = new Film();
		entity.setFilmId(dto.getFilmId());
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setLength(dto.getLength());
		entity.setFilmActors(new LinkedList<>());
		entity.setFilmCategories(new LinkedList<>());
		return entity;
	}
	
	public static FilmDTO from(Film entity) {
		FilmDTO dto = new FilmDTO();
		dto.setFilmId(entity.getFilmId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setLength(entity.getLength());
		return dto;
	}
	
}
