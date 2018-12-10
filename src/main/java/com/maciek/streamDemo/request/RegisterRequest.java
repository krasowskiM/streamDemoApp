package com.maciek.streamDemo.request;

public class RegisterRequest {
    private String email;
    private String password;
    private String passwordRetype;
    private String indexNumber;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String password, String passwordRetype, String indexNumber) {
        this.email = email;
        this.password = password;
        this.passwordRetype = passwordRetype;
        this.indexNumber = indexNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    @Override
    public String toString() {
        return "[ email=" +
                this.email +
                ", password=" +
                this.password +
                ", index number=" +
                this.indexNumber + " ]";
    }
}
