package com.cloud99.rental.repo.redis;

import com.cloud99.rental.domain.redis.AuthToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepo extends CrudRepository<AuthToken, String> {}
