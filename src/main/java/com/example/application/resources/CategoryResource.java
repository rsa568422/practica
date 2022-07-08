package com.example.application.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public List<CategoryDTO> getAll() {
		return service.getByProjection(CategoryDTO.class);
	}

	@GetMapping(params = "page")
	public Page<CategoryDTO> getAll(Pageable page) {
		return service.getByProjection(page, CategoryDTO.class);
	}

	@GetMapping(path = "/{id:\\d+}/**")
	public CategoryDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = service.getOne(id);
		if(item.isEmpty())
			throw new NotFoundException();
			
		return CategoryDTO.from(item.get());
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO dto) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = CategoryDTO.toEntity(dto);
		newItem = service.add(newItem);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item.getCategoryId() != id) 
			throw new BadRequestException("No coinciden los identificadores");
		service.modify(CategoryDTO.toEntity(item));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		service.deleteById(id);
	}

}
