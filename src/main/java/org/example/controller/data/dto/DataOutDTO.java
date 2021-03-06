package org.example.controller.data.dto;

import org.example.model.measurement.Data;

public class DataOutDTO {

    private String location;
    private Double value;
    private Long time;

    public DataOutDTO(Data data) {
        this.location = data.getLocation();
        this.value = data.getValue();
        this.time = data.getTime().toEpochMilli();
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