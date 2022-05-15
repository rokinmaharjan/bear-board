package com.rokin.baylorboard.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "whitelist_users")
public class WhitelistUser {

	@Id
	private String id;
	private String user;

}
