package com.example.domains.entities.dtos;

import java.util.LinkedList;

import com.example.domains.entities.Actor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
	
	private int actorId;

	private String firstName;

	private String lastName;
	
	public static Actor toEntity(ActorDTO dto) {
		Actor entity = new Actor();
		entity.setActorId(dto.getActorId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setFilmActors(new LinkedList<>());
		return entity;
	}
	
	public static ActorDTO from(Actor entity) {
		ActorDTO dto = new ActorDTO();
		dto.setActorId(entity.getActorId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		return dto;
	}
	
}
