package com.gonzik28.information_tape.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ResponseTweetDto {
    private String id;
    private ResponseUserDto user;
    private LocalDateTime creationTs;
    private String tweet;
    private Set<ResponseCommentDto> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResponseUserDto getResponseUserDto() {
        return user;
    }

    public void setResponseUserDto(ResponseUserDto user) {
        this.user = user;
    }

    public LocalDateTime getCreationTs() {
        return creationTs;
    }

    public void setCreationTs(LocalDateTime creationTs) {
        this.creationTs = creationTs;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Set<ResponseCommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<ResponseCommentDto> comments) {
        this.comments = comments;
    }

}
