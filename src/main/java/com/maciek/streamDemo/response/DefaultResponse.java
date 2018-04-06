package com.maciek.streamDemo.response;

import java.time.Instant;
import java.util.Date;

public class DefaultResponse {
    private static int id;
    private Date responseDate;
    private String confirmation;

    public DefaultResponse() {
        id++;
        responseDate = Date.from(Instant.now());
    }

    public DefaultResponse(String confirmation){
        id++;
        responseDate = Date.from(Instant.now());
        this.confirmation = confirmation;
    }

    public int getId() {
        return id;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public String getConfirmation() {
        return confirmation;
    }
}
