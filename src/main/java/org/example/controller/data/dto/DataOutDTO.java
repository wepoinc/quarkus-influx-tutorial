package org.example.controller.data.dto;

import java.time.Instant;


public class DataOutDTO {

    private String location;
    private Double value;
    private Long time;

    public DataOutDTO(DataInDTO data) {
        this.location = data.getCoreId();
        this.value = data.getData();
        this.time = Instant.parse(data.getPublishedAt()).toEpochMilli();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}