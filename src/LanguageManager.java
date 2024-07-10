import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class LanguageManager {
    private final Connection connection;
    private ProfileManager profileManager;

    public LanguageManager(){
        connection = Database.getCon();
        profileManager = new ProfileManager();
    }


    public boolean addUserLanguage(String username, String lang){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.CREATE_LANGUAGE_PROFILE);

            int id = profileManager.getProfileId(username);
            stm.setInt(1, id);
            stm.setString(2, lang);
            stm.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    public boolean getLanguage(String lang){
        try {
            PreparedStatement stm = connection.prepareStatement(ProfileQueries.GET_LANGUAGE);
            stm.setString(1,lang);
            ResultSet res = stm.executeQuery();
            if(res.getInt("name") == 0){
                System.out.println("THERE IS NO SUCH LANGUAGE!");
                return false;
            }
            return true;
        }catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }
}
