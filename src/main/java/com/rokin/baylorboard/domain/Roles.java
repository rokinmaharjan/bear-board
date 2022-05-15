package com.rokin.baylorboard.domain;

import com.rokin.baylorboard.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
public class Roles {
    @Id
    private String id;

    private Role name;

}
