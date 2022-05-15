package com.rokin.baylorboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.rokin.baylorboard.domain.Tweet;
import com.rokin.baylorboard.enums.TweetStatus;

public interface TweetRepository extends MongoRepository<Tweet, String> {

	Page<Tweet> findByStatus(TweetStatus status, PageRequest pageRequest);

}
