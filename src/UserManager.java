import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserManager {

    
    
    private final Connection connection;
    private ProfileManager profileManager;

    public UserManager(){
        connection = Database.getCon();
        profileManager = new ProfileManager();
    }

    public boolean create(User user){
        try{
            PreparedStatement stm = connection.prepareStatement(UserQueries.CREATE_USER);

            stm.setString(1, user.getUsername());
            stm.setString(2, user.getName());
            stm.setString(3, user.getLastname());
            if (user.getBirthdate() == null){
                stm.setDate(4, null);
            }
            else{
                stm.setDate(4, new java.sql.Date(user.getBirthdate().getTime()));
            }
            stm.setString(5, user.getPassword());
            if(!profileManager.createProfile(user.getUsername())){
                return false;
            }
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public String getUsername(String username) {
        try {
            PreparedStatement stm = connection.prepareStatement(UserQueries.GET_USERNAME);
            stm.setString(1, username);
            ResultSet res = stm.executeQuery();
            if (!res.next()){
                return null;
            }
            return res.getString("username");
        }catch (SQLException e){
            System.out.println(e);
        }
        return "";
    }

    // public boolean isExisted(String[] fields, String table){
    //     String query = "SELECT ";
    //     for (String field : fields){
    //         query += field + ",";
    //     }
    //     query = query.substring(0, query.length() - 1);
    //     query += " FROM " + table + " WHERE ";
    // } 

    public User getUser(String username , String password){
        try {
            PreparedStatement stm = connection.prepareStatement(UserQueries.GET_USER);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet res = stm.executeQuery();
            if (!res.next()){
                return null;
            }
            User user = new User(
                        res.getString("First_name"),
                        res.getString("username"),
                        res.getString("last_name"),
                        res.getString("password"),
                        res.getDate("birthdate")
            );
            return user;
        }catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public boolean setFirstnameLastname(String username, String newname, int button){
        try {
            PreparedStatement stm;
            if (button == 1){
                stm = connection.prepareStatement(UserQueries.SET_FIRSTNAME);
            }
            else{
                stm = connection.prepareStatement(UserQueries.SET_LASTNAME);
            }
            stm.setString(1, newname);
            stm.setString(2, username);
            stm.executeUpdate();
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }


}
