package com.cloud99.rental.service;

import com.cloud99.rental.domain.MongoDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.annotation.PostConstruct;

import java.util.Optional;

// TODO - NG - see if we can dynamically lookup an implementation of a REPO and then call it's method within
// the CRUD methods
public class AbstractCrudService<T extends MongoDocument<?>, REPO extends MongoRepository<?, ?>>
		implements CrudService<T, REPO> {

	@Autowired
	private ApplicationContext context;

	private REPO repo;

	@PostConstruct
	public void init() {

	}

	@Override
	public T create(T document) {
		return null;
	}

	@Override
	public Optional<T> read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T document) {
		// TODO Auto-generated method stub

	}


}
