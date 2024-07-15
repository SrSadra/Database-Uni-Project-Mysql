package model;

public class DirectModel {
    private int direct_id;
    private int user_id;
    private int to_id;
    private boolean is_readed;
    private boolean is_archived; 

    private String to_username;
    
    public DirectModel(int direct_id, int user_id, int to_id, boolean is_readed, boolean is_archived){
        this.direct_id = direct_id;
        this.user_id = user_id;
        this.to_id = to_id;
        this.is_readed = is_readed;
        this.is_archived = is_archived;
    }

    public void setDirect_id(int direct_id) {
        this.direct_id = direct_id;
    }

    public int getDirect_id() {
        return direct_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean getIs_readed(){
        return is_readed;
    }

    public boolean getIs_archived(){
        return is_archived;
    }

    public String getTo_username() {
        return to_username;
    }

    public void setTo_username(String to_username) {
        this.to_username = to_username;
    }
}
