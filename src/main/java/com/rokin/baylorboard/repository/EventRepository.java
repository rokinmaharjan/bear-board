package com.rokin.baylorboard.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rokin.baylorboard.domain.Event;

public interface EventRepository extends MongoRepository<Event, String> {

}
