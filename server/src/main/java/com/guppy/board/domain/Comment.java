package com.guppy.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by guppy.kang on 2018. 2. 4.
 * email : guppy.kang@kakaocorp.com
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment {

    @Field(value = "user_name")
    private String userName;

    @Field("message")
    private String message;

    @Field(value = "created_at")
    private DateTime createdAt;


}
