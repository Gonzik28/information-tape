package com.gonzik28.information_tape.dto;

import com.gonzik28.information_tape.config.Right;

public class ResponseRightDto {

    private String id;
    private ResponseUserDto user;
    private Right userRight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ResponseUserDto getUser() {
        return user;
    }

    public void setUser(ResponseUserDto user) {
        this.user = user;
    }

    public Right getUserRight() {
        return userRight;
    }

    public void setUserRight(Right userRight) {
        this.userRight = userRight;
    }
}
