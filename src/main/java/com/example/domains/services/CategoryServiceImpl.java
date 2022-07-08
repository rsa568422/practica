package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.CategoryRepository;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repository;

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return repository.findByCategoryIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return repository.findByCategoryIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return repository.findByCategoryIdIsNotNull(pageable, type);
	}

	@Override
	public Iterable<Category> getAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<Category> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Category> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Category> getOne(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getCategoryId()).isPresent())
			throw new DuplicateKeyException();
		return repository.save(item);
	}

	@Override
	public Category modify(Category item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getCategoryId()).isEmpty())
			throw new NotFoundException();
		return repository.save(item);
	}

	@Override
	public void delete(Category item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		deleteById(item.getCategoryId());
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
