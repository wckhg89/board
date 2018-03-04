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

    private User(Authentication authentication, String type) {
        this.setUserInfo(authentication, type);
    }

    public static User initUser(Authentication authentication, String type) {
        return new User(authentication, type);
    }

    public void setUserInfo(Authentication authentication, String type) {
        HashMap<String, String> detailMap
                = (HashMap<String, String>) authentication.getDetails();

        this.socialType = type;

        if ("kakao".equals(type)) {
            HashMap<String, String> propertyMap = (HashMap<String, String>)(Object) detailMap.get("properties");
            this.userPrincipal = String.valueOf(detailMap.get("id"));
            this.userName = propertyMap.get("nickname");
            this.setKakaoInitUserUrl(propertyMap.get("thumbnail_image"));
        }

        if ("facebook".equals(type)) {
            this.userPrincipal = detailMap.get("id");
            this.userName = detailMap.get("name");
            this.setFacebookUrl();
        }

        this.setUserKey();


    }

    public void setKakaoInitUserUrl(String userProfileUrl) {
        this.userProfileUrl = userProfileUrl;
    }


    public void setFacebookUrl() {
        StringBuilder builder = new StringBuilder();

        setFacebookInitUserProfileUrl(builder);

        builder.setLength(0);

        setFacebookInitUserUrl(builder);
    }

    private void setFacebookInitUserUrl(StringBuilder builder) {
        this.userUrl = builder
                .append("https://facebook.com/")
                .append(userPrincipal)
                .toString();
    }

    private void setFacebookInitUserProfileUrl(StringBuilder builder) {
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
