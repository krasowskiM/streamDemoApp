package com.maciek.streamDemo.response;

import com.maciek.streamDemo.RegisterStatus;

import java.util.Date;

public class RegisterResponse {
    private final RegisterStatus registerStatus;
    private final Date registerDate;

    public RegisterResponse(RegisterStatus registerStatus, Date registerDate) {
        this.registerStatus = registerStatus;
        this.registerDate = registerDate;
    }

    public RegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    @Override
    public String toString() {
        return "[register status=" +
                this.registerStatus.name() +
                ", register date=" +
                this.registerDate.toString() +
                "]";
    }
}
