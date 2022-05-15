package com.rokin.baylorboard.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.rokin.baylorboard.domain.Tweet;
import com.rokin.baylorboard.enums.TweetStatus;
import com.rokin.baylorboard.repository.TweetRepository;

@SpringBootTest
public class TweetServiceTest {
	@MockBean
	private TweetRepository tweetRepository;

	@Autowired
	private TweetService tweetService;

	private List<Tweet> acceptedTweets = new ArrayList<Tweet>();
	private List<Tweet> pendingTweets = new ArrayList<Tweet>();

	@Before
	public void initializeTweets() {
		acceptedTweets = Arrays.asList(new Tweet("1", "Hi", "rokinmaharjan", TweetStatus.ACCEPTED, new Date(), null),
				new Tweet("2", "Bye", "rokinmaharjan", TweetStatus.ACCEPTED, new Date(), null),
				new Tweet("3", "Goodnight", "rokinmaharjan", TweetStatus.ACCEPTED, new Date(), null));

		pendingTweets = Arrays.asList(new Tweet("1", "Hi", "rokinmaharjan", TweetStatus.PENDING, new Date(), null),
				new Tweet("2", "Bye", "rokinmaharjan", TweetStatus.PENDING, new Date(), null));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void findTweetsWithPagingTestSuccess() {
		Page<Tweet> tweetsPage = new PageImpl<>(acceptedTweets, PageRequest.of(0, 10), acceptedTweets.size());

		when(tweetRepository.findAll(PageRequest.of(0, 10))).thenReturn(tweetsPage);

		Map<String, Object> tweetsMap = tweetService.findTweetsWithPaging(null, 0, 10);

		assertThat(tweetsMap.size()).isEqualTo(3);
	}

	@Test
	public void findTweetsWithPagingTestWithStatus() {
		Page<Tweet> tweetsPage = new PageImpl<>(pendingTweets, PageRequest.of(0, 10), pendingTweets.size());

		when(tweetRepository.findByStatus(TweetStatus.PENDING, PageRequest.of(0, 10))).thenReturn(tweetsPage);

		Map<String, Object> tweetsMap = tweetService.findTweetsWithPaging(TweetStatus.PENDING, 0, 10);

		assertThat(tweetsMap.size()).isEqualTo(3);
	}

}
