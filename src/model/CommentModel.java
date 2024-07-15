package model;
import java.util.Date;

public class CommentModel {
    private int id;
    private int profile_id;
    private int post_id;
    private String data;
    private Date time;
    private boolean is_replied;
    private int ref_id;
    private String username;


    public CommentModel(int profile_id,int post_id ,String data, boolean is_replied){
        this.profile_id = profile_id;
        this.post_id = post_id;
        this.data = data;
        this.is_replied = is_replied;
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

    public void setRef_id(int ref_id) {
        this.ref_id = ref_id;
    }

    public int getRef_id() {
        return ref_id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getData() {
        return data;
    }

    public boolean getIs_replied(){
        return is_replied;
    }
}
