import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ProfileModel;
import model.Skill;
import model.User;

public class ProfileManager {
    
    private final Connection connection;
    private SkillManager skillManager;

    public ProfileManager(){
        connection = Database.getCon();
        skillManager = new SkillManager();
    }

    public boolean createProfile(String username){
        try{
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.CREATE_PROFILE);

            stm.setString(1, null);
            stm.setString(2, username);
            stm.setString(3, null);
            stm.setString(4, null);
            stm.setString(5, null);
            stm.setString(6, null);
            stm.setString(7, null);
            stm.setString(8, null);
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean createUserSkill(String prof_username, String skill_name){
        try {
            String skill_id = skillManager.getSkillId(skill_name);
            if (skill_id == null){
                System.out.println("There is no such skill name");
                return false;
            }
            int prof_id = getProfileId(prof_username);

            PreparedStatement stm = connection.prepareStatement(SkillQueries.CREATE_USER_SKILL);

            stm.setInt(1, prof_id);
            stm.setString(2, skill_id);
            stm.setInt(3, 0);
            stm.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    ArrayList<Skill> getUserSkills(String username){// get it from skill_profile with prodile id
        try{
            int prof_id = getProfileId(username);

            PreparedStatement stm =  connection.prepareStatement(SkillQueries.GET_USER_SKILL);
            stm.setInt(1, prof_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<Skill> arr = new ArrayList<>();
            while (rs.next()){
                arr.add(new Skill(rs.getInt("id"),rs.getString("name"), rs.getString("type"), rs.getInt("endorse_count")));
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public boolean deleteUserSkills(String username, int skill_id){
        try{
            int prof_id = getProfileId(username);

            PreparedStatement stm = connection.prepareStatement(SkillQueries.DELETE_USER_SKILL);
            
            stm.setInt(1, prof_id);
            stm.setInt(2, skill_id);
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }



    public int getProfileId(String prof_username){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_ID);
            stm.setString(1,prof_username);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return res.getInt("id");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return -1;
    }

    public boolean createAbout(String username, String about){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.UPDATE_ABOUT);

            stm.setString(1, about);
            stm.setString(2, username);
            stm.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean createBackground(User user, Blob image ,String imageName){
        try{
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.CREATE_BACKGROUND);
            // Set the parameters for the PreparedStatement
            stm.setString(1, imageName);
            stm.setBlob(2, image);
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            int id = -1;
            if (rs.next()) { 
                id = rs.getInt(1); 
            } 
            PreparedStatement stm2 = connection.prepareStatement(ProfileQueries.UPDATE_BACKGROUND_PROFILE);
            stm2.setInt(1, id);
            stm2.setString(2, user.getUsername());
            stm2.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public ProfileModel getProfileById(int profile_id){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_ID);
            stm.setInt(1,profile_id);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return new ProfileModel(profile_id, res.getString("username"), res.getString("about"));
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public ProfileModel getProfileByUsername(String username){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_USERNAME);
            // System.out.println(username);
            stm.setString(1,username);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return new ProfileModel(res.getInt("id"), res.getString("username"), res.getString("about"));
            }
            return null;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public ProfileModel getProfileByLikeUsername(String username){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_LIKE_USERNAME);
            // System.out.println(username);
            stm.setString(1,username);
            ResultSet res = stm.executeQuery();
            if (res.next()){
                return new ProfileModel(res.getInt("id"), res.getString("username"), res.getString("about"));
            }
            return null;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public boolean addPosition(int profile_id , String pos){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.UPDATE_POSITION);

            stm.setString(1, pos);
            stm.setInt(2, profile_id);
            stm.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public ArrayList<ProfileModel> getUserBaseLocation(String location){
        try{
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_LOCATION);
            stm.setString(1, location);
            ResultSet rs = stm.executeQuery();
            ArrayList<ProfileModel> arr = new ArrayList<>();
            while (rs.next()){
                arr.add(new ProfileModel(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public ArrayList<ProfileModel> getUserBaseCompany(String company){
        try{
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_PROFILE_BY_COMPANY);
            stm.setString(1, company);
            ResultSet rs = stm.executeQuery();
            ArrayList<ProfileModel> arr = new ArrayList<>();
            while (rs.next()){
                arr.add(new ProfileModel(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }





}
