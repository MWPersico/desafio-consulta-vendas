package com.devsuperior.dsmeta.dto;

import java.time.Instant;

public class CustomErrorDTO {
    private final Instant timeStamp;
    private final Integer status;
    private final String error;
    private final String path;

    public CustomErrorDTO(Instant timeStamp, Integer status, String error, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}