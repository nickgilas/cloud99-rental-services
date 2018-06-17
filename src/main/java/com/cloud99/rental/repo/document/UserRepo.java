package com.cloud99.rental.repo.document;

import com.cloud99.rental.domain.document.security.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

	@Query(value = "{ 'user.person.email' : ?0 }")
	User findByEmail(@Param("email") String email);

}
