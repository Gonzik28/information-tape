package com.gonzik28.information_tape.entity;

import com.gonzik28.information_tape.config.Right;

import javax.persistence.*;

@Entity
@Table(name = RightEntity.TABLE)
public class RightEntity {
    public static final String TABLE = "rights";

    @Id
    private String id;
    @OneToOne
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private Right userRight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Right getUserRight() {
        return userRight;
    }

    public void setUserRight(Right userRight) {
        this.userRight = userRight;
    }

}
