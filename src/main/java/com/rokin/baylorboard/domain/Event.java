package com.rokin.baylorboard.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "events")
public class Event {

	@Id
	private String id;
	private String title;
	private String coordinator;
	private String description;
	private Date date;
	private List<String> images = new ArrayList<String>();
	private List<String> videos = new ArrayList<String>();

	@CreatedDate
	private Date createdDate;
	@LastModifiedDate
	private Date lastModifiedDate;
}
