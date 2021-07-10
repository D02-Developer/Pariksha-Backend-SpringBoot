package com.pariksha.model;

public class JwtRes {

    String token;

    public JwtRes(String token) {
        this.token = token;
    }

    public JwtRes() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
