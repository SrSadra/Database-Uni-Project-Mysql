import java.sql.Connection;
// import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import model.ProfileModel;

public class NetworkManager {

    private final Connection connection;

    public NetworkManager(){
        connection = Database.getCon();
    }
    
    public ArrayList<ProfileModel> getInvitations(int profile_id){
        try{
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.GET_INVITATIONS);
            stm.setInt(1, profile_id);
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

    public boolean createInvitation(int profile_id, int to_id){
        try{
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.CREATE_INVITATION);
            stm.setInt(1, profile_id);
            stm.setInt(2, to_id);
            stm.setString(3, "pending");
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean acceptInvitation(int profile_id, int friend_id){
        try{
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.ACCEPT_INVITATIONS);
            stm.setInt(1, friend_id);
            stm.setInt(2, profile_id);
            
            PreparedStatement stm2 = connection.prepareStatement(NetworkQueries.CREATE_CONNECTIONS);
            stm2.setInt(1, friend_id);
            stm2.setInt(2, profile_id);
            Date tmp = new Date();
            stm2.setDate(3,new java.sql.Date((tmp.getTime())));
            PreparedStatement stm3 = connection.prepareStatement(NetworkQueries.CREATE_CONNECTIONS);
            stm3.setInt(1, profile_id);
            stm3.setInt(2, friend_id);


            stm.executeUpdate();
            stm2.executeUpdate();
            stm3.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public boolean ignoreInvitation(int profile_id, int friend_id){
        try{
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.IGNORE_INVITATIONS);
            stm.setInt(1, friend_id);
            stm.setInt(2, profile_id);
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public ArrayList<ProfileModel> getPeopleYouKnow(int profile_id){
        try{
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.GET_PEOPLE_YOU_KNOW);
            stm.setInt(1, profile_id);
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
