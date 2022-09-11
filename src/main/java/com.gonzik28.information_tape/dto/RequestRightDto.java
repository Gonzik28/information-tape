package com.gonzik28.information_tape.dto;

import com.gonzik28.information_tape.config.Right;

public class RequestRightDto {
    private String userId;
    private Right userRight;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Right getUserRight() {
        return userRight;
    }

    public void setUserRight(Right userRight) {
        this.userRight = userRight;
    }

}
