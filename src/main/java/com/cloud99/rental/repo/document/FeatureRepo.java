package com.cloud99.rental.repo.document;

import com.cloud99.rental.domain.document.account.Feature;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepo extends MongoRepository<Feature, String> {

}
