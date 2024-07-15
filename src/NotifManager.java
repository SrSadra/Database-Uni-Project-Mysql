import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.NotifModel;

public class NotifManager {
    private final Connection connection;

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
}
