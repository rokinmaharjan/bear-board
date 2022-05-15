package com.rokin.baylorboard.domain;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rokin.baylorboard.enums.TweetStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tweets")
public class Tweet {
	
	@Id
	private String id;
	
	private String text;
	
	private String user;
	
	private TweetStatus status;
	
	private Date tweetedAt;

	private String userImage;
}
