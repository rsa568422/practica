package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Film;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmRepository repository;

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return repository.findByFilmIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return repository.findByFilmIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return repository.findByFilmIdIsNotNull(pageable, type);
	}

	@Override
	public Iterable<Film> getAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<Film> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Film> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Film> getOne(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getFilmId()).isPresent())
			throw new DuplicateKeyException();
		return repository.save(item);
	}

	@Override
	public Film modify(Film item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getFilmId()).isEmpty())
			throw new NotFoundException();
		return repository.save(item);
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		deleteById(item.getFilmId());
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
