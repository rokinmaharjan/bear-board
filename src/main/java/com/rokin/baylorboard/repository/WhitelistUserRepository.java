package com.rokin.baylorboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rokin.baylorboard.domain.WhitelistUser;

public interface WhitelistUserRepository extends MongoRepository<WhitelistUser, String> {

}
