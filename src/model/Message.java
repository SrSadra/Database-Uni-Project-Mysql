package model;

import java.util.Date;

public class Message {
    private int message_id;
    private int direct_id;
    private int from_id;
    private int to_id;
    private String data;
    private Date time; 
    
    public Message(int message_id,int from_id, int to_id, String data, Date time){
        this.message_id = message_id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.data = data;
        this.time = time;
    }   

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }


    public String getData() {
        return data;
    }

    public int getDirect_id() {
        return direct_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public int getMessage_id() {
        return message_id;
    }

    public Date getTime() {
        return time;
    }

    public int getTo_id() {
        return to_id;
    }
}
