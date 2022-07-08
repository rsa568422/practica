package com.example.domains.entities.dtos;

import java.util.LinkedList;

import com.example.domains.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	
	private int categoryId;

	private String name;
	
	public static Category toEntity(CategoryDTO dto) {
		Category entity = new Category();
		entity.setCategoryId(dto.getCategoryId());
		entity.setName(dto.getName());
		entity.setFilmCategories(new LinkedList<>());
		return entity;
	}
	
	public static CategoryDTO from(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCategoryId(entity.getCategoryId());
		dto.setName(entity.getName());
		return dto;
	}

}
