package com.maciek.streamDemo.response;

public class SocketIOResponse {
    private int EIO;
    private String token;

    public SocketIOResponse(){
    }

    public int getEIO() {
        return EIO;
    }

    public void setEIO(int EIO) {
        this.EIO = EIO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
