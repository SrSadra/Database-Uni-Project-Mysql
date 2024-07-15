package model;

import java.sql.Date;

public class NotifModel {
    private int notif_id;
    private String why;
    private int caused_by_id;
    private Date time;
    private int cause_no;
    private String caused_username;


    public NotifModel(String why, int caused_by_id, int cause_no, String caused_username){
        this.why = why;
        this.caused_by_id = caused_by_id;
        this.cause_no = cause_no;
        this.caused_username = caused_username;
    }   

    public String getCaused_username() {
        return caused_username;
    }

    public int getCause_no() {
        return cause_no;
    }

    public int getCaused_by_id() {
        return caused_by_id;
    }

    public int getNotif_id() {
        return notif_id;
    }

    public Date getTime() {
        return time;
    }

    public String getWhy() {
        return why;
    }
}
