package com.guppy.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by guppy.kang on 2018. 2. 4.
 * email : guppy.kang@kakaocorp.com
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "board")
public class Board {

    @Field(value = "title")
    private String title;

    @Field(value = "content")
    private String content;

    @Field(value = "user")
    private User user;

    @Field(value = "created_at")
    private DateTime createdAt;

    @Field(value = "comments")
    private List<Comment> comments;


    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }
}
