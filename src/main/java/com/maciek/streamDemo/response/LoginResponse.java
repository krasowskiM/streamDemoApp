package com.maciek.streamDemo.response;

import com.maciek.streamDemo.LoginStatus;

import java.util.Date;

public class LoginResponse {

    private final LoginStatus loginStatus;
    private final Date loginDate;

    public LoginResponse(LoginStatus loginStatus, Date loginDate) {
        this.loginStatus = loginStatus;
        this.loginDate = loginDate;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    @Override
    public String toString() {
        return "[" + "login status=" + this.loginStatus.name() + ", login date=" + this.loginDate.toString() + "]";
    }
}
