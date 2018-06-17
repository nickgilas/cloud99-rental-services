package com.cloud99.rental.repo.cache;

import com.cloud99.rental.domain.cache.AuthenticatedUserToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepo extends CrudRepository<AuthenticatedUserToken, String> {
}
