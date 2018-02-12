package com.guppy.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.HashMap;

/**
 * Created by guppy.kang on 2018. 2. 7.
 * email : guppy.kang@kakaocorp.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {

    @Field("user_name")
    private String userName;

    @Field("user_principal")
    private String userPrincipal;

    @Field("social_type")
    private String socialType;

    @Field("user_key")
    private String userKey;

    @Field("user_profile_url")
    private String userProfileUrl;

    @Field("user_url")
    private String userUrl;

    private User(Authentication authentication) {
        this.setUserInfo(authentication);
    }

    public static User initUser(Authentication authentication) {
        return new User(authentication);
    }

    public void setUserInfo(Authentication authentication) {
        HashMap<String, String> detailMap
                = (HashMap<String, String>) authentication.getDetails();

        this.userName = detailMap.get("name");
        this.userPrincipal = detailMap.get("id");
        this.socialType = "FACEBOOK"; // TODO: 다른 소셜 붙일때 수정 필요

        this.setUserKey();
        this.setUrl();
    }

    public void setUrl() {
        StringBuilder builder = new StringBuilder();

        setInitUserProfileUrl(builder);

        builder.setLength(0);

        setInitUserUrl(builder);
    }



    private void setInitUserUrl(StringBuilder builder) {
        this.userUrl = builder
                .append("https://facebook.com/")
                .append(userPrincipal)
                .toString();
    }

    private void setInitUserProfileUrl(StringBuilder builder) {
        this.userProfileUrl = builder
                .append("http://graph.facebook.com/")
                .append(userPrincipal)
                .append("/picture?type=square")
                .append("&width=30")
                .append("&height=30")
                .toString();
    }

    public void setUserKey() {
        final String salt = "$2a$10$/L4lnbQy2Myy//jnnuHBNO";

        StringBuilder builder = new StringBuilder();
        builder.append(userName).append(userPrincipal).append(socialType);

        this.userKey = BCrypt.hashpw(builder.toString(), salt);
    }
}
