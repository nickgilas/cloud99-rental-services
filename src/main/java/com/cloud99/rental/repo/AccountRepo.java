package com.cloud99.rental.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cloud99.rental.domain.Account;

@Repository
public interface AccountRepo extends MongoRepository<Account, String> {

}
