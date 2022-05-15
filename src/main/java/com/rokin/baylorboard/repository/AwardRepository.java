package com.rokin.baylorboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rokin.baylorboard.domain.Award;

public interface AwardRepository extends MongoRepository<Award, String> {

}
