package com.example.databasetext.datautil;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by panzq on 2017/4/11.
 */
public class UserInfo implements Serializable {
    public static final String ID = "_id";
    public static final String USERID = "userId";
    public static final String TOKEN = "token";
    public static final String TOKENSECRET = "tokenSecret";
    public static final String USERNAME = "userName";
    public static final String USERICON = "userIcon";

    private String id;
    private String userId; // 用户id
    private String token;
    private String tokenSecret;
    private String userName;
    private Drawable userIcon;
    //getter and setter省略

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Drawable getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(Drawable userIcon) {
        this.userIcon = userIcon;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", tokenSecret='" + tokenSecret + '\'' +
                ", userName='" + userName + '\'' +
                ", userIcon=" + userIcon +
                '}';
    }
}
