package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.LanguageRepository;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private LanguageRepository repository;

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return repository.findByLanguageIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return repository.findByLanguageIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return repository.findByLanguageIdIsNotNull(pageable, type);
	}

	@Override
	public Iterable<Language> getAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Page<Language> getAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<Language> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Language> getOne(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getLanguageId()).isPresent())
			throw new DuplicateKeyException();
		return repository.save(item);
	}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsMessage());
		if(getOne(item.getLanguageId()).isEmpty())
			throw new NotFoundException();
		return repository.save(item);
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		deleteById(item.getLanguageId());
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
