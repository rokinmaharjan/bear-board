package com.rokin.baylorboard.domain;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "awards")
public class Award {

	@Id
	private String id;
	private String title;
	private String description;
	private String date;

	@CreatedDate
	private String createdDate;
	@LastModifiedDate
	private String lastModifiedDate;
}
