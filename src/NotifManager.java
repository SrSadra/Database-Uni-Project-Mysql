import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import model.NotifModel;
import model.ProfileModel;

public class NotifManager {
    private final Connection connection;
    private PostManager postManager;

    public NotifManager(){
        connection = Database.getCon();
    }

    public ArrayList<NotifModel> getUserNotif(int profile_id){
        try{
            PreparedStatement stm =  connection.prepareStatement(NotifQueries.GET_USER_NOTIF);
            stm.setInt(1, profile_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<NotifModel> arr = new ArrayList<>();
            while (rs.next()){
                NotifModel tmp = new NotifModel(rs.getString(2), rs.getInt(3), rs.getInt(5), rs.getString("username"));
                arr.add(tmp);
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public boolean createNotif(int profile_id, NotifModel notifModel, int button){//to profile_id will be the notif sent
        try{
            if (getNotif(notifModel.getCaused_by_id(), button, profile_id)){// dont send a notif twice
                return false;
            }
            PreparedStatement stm = connection.prepareStatement(NotifQueries.CREATE_NOTIF, Statement.RETURN_GENERATED_KEYS);

            Date tmp = new Date();
            stm.setString(1,notifModel.getWhy());
            stm.setInt(2, notifModel.getCaused_by_id());
            stm.setDate(3, new java.sql.Date((tmp.getTime())));
            stm.setInt(4, button);
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            int notif_id = 0;
            if (rs.next()){
                notif_id = rs.getInt(1);
            }

            PreparedStatement stm2 = connection.prepareStatement(NotifQueries.CREATE_PROF_NOTIF);

            stm2.setInt(1,profile_id);
            stm2.setInt(2, notif_id);
            stm2.setString(3, "unseen");
            stm2.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean getNotif(int caused_by_id, int cause_no, int profile_id){
        try{
        PreparedStatement stm =  connection.prepareStatement(NotifQueries.GET_SPECIFIC_NOTIF);
        stm.setInt(1, caused_by_id);
        stm.setInt(2, cause_no);
        stm.setInt(3, profile_id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }catch (SQLException e){
        System.out.println(e);
    }
    return false;
    }


    public boolean positionNotif(int profile_id, String username){
        try{
            PreparedStatement stm =  connection.prepareStatement(NetworkQueries.GET_CONNECTIONS_ID);
            stm.setInt(1, profile_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int friend_id = rs.getInt("friend_id");
                if (!createNotif(friend_id, new NotifModel(username + " has changed its position!", profile_id, 7, username), 7)){ // also we can use insert (), (), () ....
                    return false;
                }
            }
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

}
