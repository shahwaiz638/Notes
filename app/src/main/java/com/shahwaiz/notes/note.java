package com.shahwaiz.notes;

public class note {


    int id;
    String Title,description;
    long Currenttime,updatedTime;
    String uri;

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public note(int id, String title, String description, long time,long updatedTime,String uri) {
        this.id = id;
        Title = title;
        this.description = description;
        this.Currenttime = time;
        this.uri=uri;
        this.updatedTime=updatedTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCurrentTime() {
        return Currenttime;
    }

    public void setCurrentTime(long time) {
        this.Currenttime = time;
    }
}
