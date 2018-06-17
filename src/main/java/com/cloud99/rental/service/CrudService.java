package com.cloud99.rental.service;

import com.cloud99.rental.domain.document.MongoDocument;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CrudService<T extends MongoDocument<?>, REPO extends MongoRepository<?, ?>> extends RentalService {

	T create(T document);
    Optional<T> read(String id );
    T update(T document);
    void delete(T document);
    
}
