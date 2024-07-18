package model;

public class ProfileModel {
    private int profile_id;
    private String username;
    private String about;
    // private String lastname;
    
    public ProfileModel(int profile_id , String username, String about){ 
        this.profile_id = profile_id;
        this.username = username;
        this.about = about;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public String getUsername() {
        return username;
    }

    public String getAbout() {
        return about;
    }


}
