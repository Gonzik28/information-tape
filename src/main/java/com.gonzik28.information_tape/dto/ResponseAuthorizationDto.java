package com.gonzik28.information_tape.dto;

public class ResponseAuthorizationDto {
    private String id;
    private String login;
    private ResponseUserDto user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ResponseUserDto getResponseUserDto() {
        return user;
    }

    public void setResponseUserDto(ResponseUserDto user) {
        this.user = user;
    }

}
