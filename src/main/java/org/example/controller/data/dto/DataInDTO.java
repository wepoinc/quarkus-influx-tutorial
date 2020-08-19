package org.example.controller.data.dto;


public class DataInDTO {

    private String event;
    private Double data;
    private String coreId;
    private String publishedAt;

    public DataInDTO() {
        event = "";
        data = 0.0;
        coreId = "";
        publishedAt = "";
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }

    public String getCoreId() {
        return coreId;
    }

    public void setCoreId(String coreId) {
        this.coreId = coreId;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}