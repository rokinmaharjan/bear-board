package com.rokin.baylorboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rokin.baylorboard.domain.Tweet;
import com.rokin.baylorboard.enums.TweetStatus;
import com.rokin.baylorboard.service.TweetService;

import twitter4j.TwitterException;

@RestController
@RequestMapping("/tweets")
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@GetMapping("/fetch")
	public String fetchTweets() throws TwitterException {
		return tweetService.streamTweets();
	}

	@GetMapping
	public Map<String, Object> getTweets(@RequestParam(required = false) TweetStatus status,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "100") Integer size) {
		return tweetService.findTweetsWithPaging(status, page, size);
	}

	@PutMapping("/{id}/status")
	public Tweet updateTweetStatus(@PathVariable String id, @RequestParam TweetStatus status) {
		return tweetService.updateStatus(id, status);
	}

	@GetMapping("/{id}")
	public Tweet getById(@PathVariable String id) {
		return tweetService.getById(id);
	}

	@DeleteMapping
	public String deleteAllTweets() {
		tweetService.deleteAllTweets();
		return "Deleted all tweets";
	}

}
