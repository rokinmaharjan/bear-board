package com.rokin.baylorboard.repository;

import com.rokin.baylorboard.domain.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Roles, String> {
    Roles findByName(String role);
}
