package com.guppy.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Locale;

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

    @JsonIgnore
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(new Locale("ko"));

    @Field(value = "title")
    private String title;

    @JsonProperty("contents")
    @Field(value = "content")
    private String content;

    @JsonIgnore
    @Field(value = "user")
    private User user;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("social_type")
    private String socialType;

    @Field(value = "created_at")
    private DateTime createdAt;

    @JsonProperty("afterPeriod")
    private String afterPeriod;

    @Field(value = "comments")
    private List<Comment> comments;


    @JsonIgnore
    public void setUser(User user) {
        this.user = user;

    }
    
    public String getSocialType() {
        this.socialType = user != null ? user.getSocialType() : null;

        return socialType;
    }

    public String getUserName() {
        this.userName = user != null ? user.getUserName() : null;
        return userName;
    }

    public String getAfterPeriod() {
        DateTime now = new DateTime();
        Period period = new Period(this.createdAt, now);

        this.afterPeriod = this.calculateAfterPeriod(period, this.createdAt);

        return afterPeriod;
    }

    /**
     * 현재시간과 특정날짜의 시간이 어느정도 기간이었는지 계산해주는 메소드이다.
     * @param period
     * @param dateTime
     * @return
     */
    private String calculateAfterPeriod (Period period, DateTime dateTime) {

        int years = period.getYears();
        int months = period.getMonths();
        int weeks = period.getWeeks();
        int days = period.getDays();
        int hours = period.getHours();
        int minutes = period.getMinutes();


        if (years != 0) {
            return dateTimeFormatter.print(dateTime);
        }

        if (months > 1) {
            return dateTimeFormatter.print(dateTime);
        }


        if (months !=0 && months < 2) {
            return months + "달전";
        }

        if (weeks !=0 && weeks < 5) {
            return weeks + "주전";
        }

        if (days != 0 && days < 7) {
            return days + "일전";
        }

        if (hours !=0 && hours < 24) {
            return hours + "시간전";
        }

        if (minutes !=0 && minutes < 60) {
            return minutes + "분전";
        }

        return "방금전";

    }
}
