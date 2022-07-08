package com.example.domains.entities.dtos;

import java.util.LinkedList;

import com.example.domains.entities.Language;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDTO {

	private int languageId;

	private String name;
	
	public static Language toEntity(LanguageDTO dto) {
		Language entity = new Language();
		entity.setLanguageId(dto.getLanguageId());
		entity.setName(dto.getName());
		entity.setFilms(new LinkedList<>());
		entity.setFilmsVO(new LinkedList<>());
		return entity;
	}
	
	public static LanguageDTO from(Language entity) {
		LanguageDTO dto = new LanguageDTO();
		dto.setLanguageId(entity.getLanguageId());
		dto.setName(entity.getName());
		return dto;
	}
	
}
