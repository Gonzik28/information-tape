package com.gonzik28.information_tape.dto;

import java.time.LocalDateTime;

public class ResponseCommentDto {
    private String id;
    private String comment;
    private LocalDateTime createTs;
    private ResponseUserDto user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreateTs() {
        return createTs;
    }

    public void setCreateTs(LocalDateTime createTs) {
        this.createTs = createTs;
    }

    public ResponseUserDto getUser() {
        return user;
    }

    public void setUser(ResponseUserDto user) {
        this.user = user;
    }

}
