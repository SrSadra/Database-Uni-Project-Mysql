import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DirectModel;
import model.Message;
import model.ProfileModel;

public class DirectManager {

    private final Connection connection;
    private SkillManager skillManager;

    public DirectManager(){
        connection = Database.getCon();
        skillManager = new SkillManager();
    }


    public ArrayList<ProfileModel> getConnections(int profile_id){
        try {
            // PreparedStatement stm = connection.prepareStatement(NetworkQueries.GET_CONNECTIONS);
            // stm.setInt(1,profile_id);
            // ResultSet res = stm.executeQuery();
            // if(!res.next()){
            //     System.out.println("THERE IS NO CONNECTIONS!");
            //     return null;
            // }
            PreparedStatement stm = connection.prepareStatement(NetworkQueries.GET_CONNECTIONS);
            stm.setInt(1,profile_id);
            ResultSet res = stm.executeQuery();
            //still dont know
            ArrayList<ProfileModel> arr = new ArrayList<>();
            while(res.next()){
                arr.add(new ProfileModel(res.getInt("p.id"), res.getString("p.username"), res.getString("p.about")));
            }
            return arr;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public int createDirect(int profile,int to){
        try {
            PreparedStatement stm1 = connection.prepareStatement(DirectQueries.GET_DIRECT);
            stm1.setInt(1, profile);
            stm1.setInt(2, to);
            ResultSet res1 = stm1.executeQuery();
            if(res1.next()){
                System.out.println("You have already a chat");
                return res1.getInt(1);
            }
            PreparedStatement stm = connection.prepareStatement(DirectQueries.CREATE_DIRECT, Statement.RETURN_GENERATED_KEYS);

            stm.setInt(1, profile);
            stm.setInt(2, to);

            PreparedStatement stm2 = connection.prepareStatement(DirectQueries.CREATE_DIRECT);

            stm2.setInt(1, to);
            stm2.setInt(2, profile);


            stm.executeUpdate();
            stm2.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }


    public ArrayList<Message> getDirectMessages(int direct_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.GET_MESSAGES_BY_DIRECT_ID);

            stm.setInt(1, direct_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<Message> arr = new ArrayList<>();
            while (rs.next()){
                arr.add(new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
            }
            return arr;

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public boolean createMessage(Message message){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.CREATE_MESSAGE, Statement.RETURN_GENERATED_KEYS);
            // Set the parameters for the PreparedStatement
            stm.setInt(1, message.getFrom_id());
            stm.setInt(2, message.getTo_id());
            stm.setString(3, message.getData());
            stm.setDate(4, new java.sql.Date(message.getTime().getTime()));
            stm.executeUpdate();
            ResultSet rs1 = stm.getGeneratedKeys();
            int message_key = 0;
            if (rs1.next()){
                message_key = rs1.getInt(1);
            }

            PreparedStatement stm2 = connection.prepareStatement(DirectQueries.CREATE_MESSAGE_DIRECT);
            int from_direct_id = getDirectId(message.getFrom_id(),message.getTo_id());
            stm2.setInt(1, message_key);
            stm2.setInt(2, from_direct_id);
            stm2.executeUpdate();


            PreparedStatement stm3 = connection.prepareStatement(DirectQueries.CREATE_MESSAGE_DIRECT);
            int to_direct_id = getDirectId(message.getTo_id(), message.getFrom_id());
            stm3.setInt(1, message_key);
            stm3.setInt(2, to_direct_id);
            stm3.executeUpdate();
            
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }


    public int getDirectId(int profile_id, int to_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.GET_DIRECT_ID);
            stm.setInt(1, profile_id);
            stm.setInt(2, to_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
            return -1;
        }catch (SQLException e){
            System.out.println(e);
        }
        return -1;
    }


    public ArrayList<DirectModel> getDirects(int profile_id, int butt){
        try{
            PreparedStatement stm = null;
            if (butt == 0){
                stm = connection.prepareStatement(DirectQueries.GET_USER_DIRECTS);
                stm.setInt(1, profile_id);
            }
            else if (butt == 1){
                stm = connection.prepareStatement(DirectQueries.GET_DIRECT_UNREADED);
                stm.setInt(1, profile_id); //actually direct_id
            }
            else if (butt == 2){
                stm = connection.prepareStatement(DirectQueries.GET_DIRECT_ARCHIVED);
                stm.setInt(1, profile_id); //actually direct_id
            }
            ResultSet rs = stm.executeQuery();
            ArrayList<DirectModel> arr = new ArrayList<>();
            while (rs.next()){
                DirectModel tmp = new DirectModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getBoolean(5));
                if (butt != 0){
                    tmp.setTo_username(rs.getString(6));
                }
                arr.add(tmp);
            }
            return arr;
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }


    public boolean markUnread(int direct_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.MARK_DIRECT_UNREAD);

            stm.setInt(1, direct_id);
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean archieveDirect(int direct_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.ARCHIVE_DIRECT);

            stm.setInt(1, direct_id);
            stm.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteDirect(int direct_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.DELETE_DIRECT);
            stm.setInt(1, direct_id);
            
            PreparedStatement stm2 = connection.prepareStatement(DirectQueries.DELETE_MESSAGE_DIRECT);
            stm2.setInt(1, direct_id);
            
            stm.executeUpdate();
            stm2.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<DirectModel> searchMessage(String text, int profile_id){
        try{
            PreparedStatement stm = connection.prepareStatement(DirectQueries.GET_DIRECT_BY_SEARCH);

            String tmp = "%" + text + "%";
            stm.setString(1, tmp);
            stm.setInt(2, profile_id);
            ResultSet rs = stm.executeQuery();
            ArrayList<DirectModel> arr = new ArrayList<>();
            while (rs.next()){
                DirectModel dModel = new DirectModel(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4), rs.getBoolean(5));
                dModel.setTo_username(rs.getString(6));
                arr.add(dModel);
            }
            return arr;

        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
