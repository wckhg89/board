package com.guppy.board.domain;

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
@Document(collection = "board")
public class Board {

    @Field(value = "title")
    private String title;

    @Field(value = "content")
    private String content;

    @Field(value = "user_name")
    private String userName;

    @Field(value = "created_at")
    private DateTime createdAt;

    @Field(value = "comments")
    private List<Comment> comments;


}
