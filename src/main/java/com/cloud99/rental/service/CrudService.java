package com.cloud99.rental.service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloud99.rental.domain.MongoDocument;

public  interface CrudService<T extends MongoDocument, REPO extends MongoRepository<?, ?>> extends RentalService {

    T create(T document);
    Optional<T> read(String id );
    T update(T document);
    void delete(T document);
    
}
