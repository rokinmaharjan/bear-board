package com.rokin.baylorboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.rokin.baylorboard.controller.GreetingController;
import com.rokin.baylorboard.domain.MessageBean;
import com.rokin.baylorboard.domain.Tweet;
import com.rokin.baylorboard.domain.WhitelistUser;
import com.rokin.baylorboard.enums.TweetStatus;
import com.rokin.baylorboard.repository.TweetRepository;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;

@Service
public class TweetService {

	@Autowired
	private Twitter twitter;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private WhitelistUserService whitelistUserService;

	@Autowired
	private TwitterStream twitterStream;

	@Autowired
	private GreetingController greet;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafka.tweetTopic}")
	private String kafkaTweetTopic;

	private static final String SEARCH_KEYWORD = "Baylor";

	//	@Scheduled(cron = "0 1 1 * * ?")
	public List<Tweet> fetchTweets() throws TwitterException {
		Query query = new Query(SEARCH_KEYWORD);
		QueryResult result = twitter.search(query);

		Set<String> whitelistUsernames = whitelistUserService.getWhitelistUsers().stream().map(WhitelistUser::getUser)
				.collect(Collectors.toSet());

		List<Tweet> tweets = new ArrayList<Tweet>();
		for (Status status : result.getTweets()) {
			// Remove re-tweets
			if (status.getRetweetedStatus() != null) {
				continue;
			}

			Tweet tweet = Tweet.builder().text(status.getText()).user(status.getUser().getScreenName())
					.userImage(status.getUser().getProfileImageURL()).tweetedAt(status.getCreatedAt()).build();

			if (whitelistUsernames.contains(tweet.getUser())) {
				tweet.setStatus(TweetStatus.ACCEPTED);
			} else {
				tweet.setStatus(TweetStatus.PENDING);
			}

			tweets.add(tweet);
		}

		return tweetRepository.saveAll(tweets);
	}

	private void sendTweetsToKafka(Tweet tweet) {
		System.out.println("Sending to kafka: " + tweet.toString());
		kafkaTemplate.send(kafkaTweetTopic, tweet.toString());
	}

	@PostConstruct
	public String streamTweets() {
		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception e) {
				e.printStackTrace();
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg) {
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}

			@Override
			public void onStatus(Status status) {
				Set<String> whitelistUsernames = whitelistUserService.getWhitelistUsers().stream()
						.map(WhitelistUser::getUser).collect(Collectors.toSet());

				if (status.getRetweetedStatus() != null) {
					return;
				}

				Tweet tweet = Tweet.builder().text(status.getText()).user(status.getUser().getScreenName())
						.userImage(status.getUser().getProfileImageURL()).tweetedAt(status.getCreatedAt()).build();

				if (whitelistUsernames.contains(tweet.getUser())) {
					tweet.setStatus(TweetStatus.ACCEPTED);
				} else {
					tweet.setStatus(TweetStatus.PENDING);
				}

				tweetRepository.save(tweet);

				MessageBean tweetmsg = new MessageBean();
				tweetmsg.setMessage("a");
				tweetmsg.setName(tweet.getId());
				greet.sendNews(tweetmsg);

				sendTweetsToKafka(tweet);
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}
		};

		twitterStream.addListener(listener);

		twitterStream.filter(SEARCH_KEYWORD);

		return "Twitter streaming tweets with #Baylor";
	}
	

	public Map<String, Object> findTweetsWithPaging(TweetStatus status, Integer page, Integer size) {
		Page<Tweet> tweets = null;

		if (status == null) {
			tweets = tweetRepository.findAll(PageRequest.of(page, size));
		} else {
			tweets = tweetRepository.findByStatus(status, PageRequest.of(page, size));
		}

		Map<String, Object> tweetsMap = new HashMap<>();
		tweetsMap.put("tweets", tweets.getContent());
		tweetsMap.put("pages", tweets.getTotalPages());
		tweetsMap.put("totalTweets", tweets.getTotalElements());

		return tweetsMap;
	}

	public Tweet updateStatus(String id, TweetStatus status) {
		Tweet tweet = tweetRepository.findById(id).get();
		tweet.setStatus(status);

		return tweetRepository.save(tweet);
	}

	public Tweet getById(String id) {
		return tweetRepository.findById(id).get();
	}

	public void deleteAllTweets() {
		tweetRepository.deleteAll();
	}

}
