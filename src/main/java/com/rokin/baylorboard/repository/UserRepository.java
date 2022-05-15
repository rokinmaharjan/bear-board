package com.rokin.baylorboard.repository;

import com.rokin.baylorboard.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAddress(String emailAddress);
}
