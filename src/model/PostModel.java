package model;

import java.util.Date;

public class PostModel {
    private int id;
    private int profile_id;
    private String title;
    private String data;
    private Date time;
    private String username;


    public PostModel(int profile_id, String title, String data){
        this.profile_id = profile_id;
        this.title = title;
        this.data = data;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public String getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }
}
